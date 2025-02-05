package com.ColumbusEventAlertService.utils;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class DynamoDBReader {

    public List<Map<String, AttributeValue>> getTodaysEvents(DynamoDbClient dynamoDbClient) {
        ZoneId zone = ZoneId.of("America/New_York");
        String todaysDate = Instant.now().atZone(zone).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        Map<String, AttributeValue> key = Map.of(
                "PrimaryKey", AttributeValue.builder().s("YourPrimaryKeyValue").build()
        );

        // Create the scan request with a filter expression to match today's date
        // filterExpression will not accept "_airbyte_data" with the underscore. This maps it to the value of #airbyte_data as a workaround.
        // filterExpression will not accept "date" since it is a reserved keyword. This maps it to the value of #date as a workaround.
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("airbyte_sync_ColumbusEvents")
                .filterExpression("attribute_exists(#airbyte_data.#date) AND #airbyte_data.#date = :todayDate")
                .expressionAttributeNames(Map.of(
                        "#airbyte_data", "_airbyte_data",
                        "#date", "date"
                ))
                .expressionAttributeValues(Map.of(
                        ":todayDate", AttributeValue.builder().s(todaysDate).build()
                ))
                .build();

        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
        List<Map<String, AttributeValue>> items = scanResponse.items();
        dynamoDbClient.close();
        return items;
    }
}
