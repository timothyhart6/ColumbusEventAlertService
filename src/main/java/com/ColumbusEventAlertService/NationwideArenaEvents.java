package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;

public class NationwideArenaEvents {

    public Event getUpcomingEvent() throws IOException {
        String googleUrl = "https://www.google.com/search?q=nationwide+arena+events&gl=us";
        Document doc = Jsoup.connect(googleUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36").get();
        Node eventNode = doc.select("div.EyBRub").get(1).child(2).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0);

        String name = eventNode.childNode(0).childNode(0).childNode(0).toString().trim();
        String date = eventNode.childNode(1).childNode(0).childNode(0).toString().trim();
        String time = eventNode.childNode(1).childNode(1).childNode(0).toString().trim();

        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        event.setTime(time);

        return event;
    }
}
