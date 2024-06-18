package com.ColumbusEventAlertService.services.columbusEvents;

import com.ColumbusEventAlertService.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

@Slf4j
public class NationwideEventServiceImpl extends EventServiceImpl {

    public NationwideEventServiceImpl(String url, JsoupService jsoupService, DateUtil dateUtil, String locationName) {
        super(url, jsoupService, dateUtil, locationName);
    }

    @Override
    protected String getEventName(Document doc) {
       return doc.getElementsByClass("title title-withTagline ").get(0).childNode(1).childNode(0).toString();
    }

    @Override
    protected String getTime(Document doc) {
        return doc.getElementsByClass("time").get(0).getElementsByClass("start").get(0).childNode(0).toString();
    }
    @Override
    protected String getDateYear(Document doc) {
        Node yearNode = doc.getElementsByClass("m-date__year").get(0).childNode(0);
        return yearNode.toString().replaceAll("\\D", "");
    }
    @Override
    protected String getDateMonth(Document doc, DateUtil dateUtil) {
        Node monthNode = doc.getElementsByClass("m-date__month").get(0).childNode(0);
        String dateMonth = monthNode.toString().trim();
        dateMonth = dateUtil.convertMonthNameToNumber(dateMonth);
        return dateMonth;
    }
    @Override
    protected String getDateDay(Document doc, DateUtil dateUtil) {
        Node dayNode = doc.getElementsByClass("m-date__day").get(0).childNode(0);
        String dateDay = dayNode.toString().replaceAll("\\D", "");
        dateDay = dateUtil.formatDay(dateDay);
        return dateDay;
    }
}
