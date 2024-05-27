package com.ColumbusEventAlertService.services.columbusEvents;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

import java.io.IOException;

public interface JsoupService {
    Connection connect(String url) throws IOException;
    Document getDocument(Connection connection) throws IOException;
}
