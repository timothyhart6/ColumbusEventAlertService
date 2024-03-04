package com.ColumbusEventAlertService;

import com.ColumbusEventAlertService.models.Event;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.IOException;

public class NationwideArenaEvents {
    @Setter
    private String googleUrl = "https://www.google.com/search?q=nationwide+arena+events&gl=us";

    public Event getUpcomingEvent() throws IllegalArgumentException {
        Event event = new Event();
        try {
            Document doc = Jsoup.connect(googleUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36").get();
            Node eventNode = doc.select("div.EyBRub").get(1).child(2).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0);

            String name = eventNode.childNode(0).childNode(0).childNode(0).toString().trim();
            String date = eventNode.childNode(1).childNode(0).childNode(0).toString().trim();
            String time = eventNode.childNode(1).childNode(1).childNode(0).toString().trim();

            event.setName(name);
            event.setDate(date);
            event.setTime(time);

        } catch (IllegalArgumentException | IOException e) {
            throw new IllegalArgumentException("Invalid URL: " + googleUrl);
        }
        return event;
    }
}
