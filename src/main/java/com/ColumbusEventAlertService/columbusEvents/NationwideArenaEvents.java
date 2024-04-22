package com.ColumbusEventAlertService.columbusEvents;

import com.ColumbusEventAlertService.models.NationwideEvent;
import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class NationwideArenaEvents {
    @Setter
    private String googleUrl = "https://www.google.com/search?q=nationwide+arena+events&gl=us";

    public NationwideEvent getUpcomingEvent() throws IllegalArgumentException {
        NationwideEvent event = new NationwideEvent();
        String time;
        try {
            Document doc = Jsoup.connect(googleUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36").get();
            Node eventNode = doc.select("div.EyBRub").get(1).child(2).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0).childNode(0);

            String name = eventNode.childNode(0).childNode(0).childNode(0).toString().trim();
            String date = eventNode.childNode(1).childNode(0).childNode(0).toString().trim();
            try {
                time = eventNode.childNode(1).childNode(1).childNode(0).toString().trim();
            } catch (IndexOutOfBoundsException e) {
                time = "UNKOWN";
            }
            event.setName(name);
            event.setDate(new DateUtil().formatGoogleDate(date));
            event.setTime(time);

        } catch (IllegalArgumentException | IOException e) {
            throw new IllegalArgumentException("Invalid URL: " + googleUrl);
        }
        return event;
    }
}