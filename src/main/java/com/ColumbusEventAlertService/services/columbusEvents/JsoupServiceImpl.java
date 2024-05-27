package com.ColumbusEventAlertService.services.columbusEvents;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupServiceImpl implements JsoupService {
    @Override
    public Connection connect(String url) {
        return Jsoup.connect(url);
    }

    @Override
    public Document getDocument(Connection connection) throws IOException {
        return connection.get();
    }
}
