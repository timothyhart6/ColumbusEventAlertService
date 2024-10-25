package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.services.TextMessageService;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@Slf4j
public class StreamLambdaHandler implements RequestStreamHandler {

    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;

    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        try {
            // Proxy the AWS request through the Spring Boot handler
            handler.proxyStream(inputStream, outputStream, context);

            // Fetch the Spring application context
            ApplicationContext springContext = (ApplicationContext) handler.getServletContext();

            // Fetch the TextMessageService bean from the Spring context
            TextMessageService textMessageService = springContext.getBean(TextMessageService.class);

            // Call the sendTodaysEvents method
            textMessageService.sendTodaysEvents();

        } catch (Exception e) {
            log.error("Error in Lambda function", e);
            throw new RuntimeException("Lambda function execution failed", e);
        }
    }
}