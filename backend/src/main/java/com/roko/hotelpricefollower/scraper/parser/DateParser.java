package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.FinnishMonth;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateParser {

    protected List<LocalDate> parseDates(Document priceMatrix) {
        return constructDates(getVisibleDateElements(priceMatrix), parseYear(priceMatrix));
    }

    private List<LocalDate> constructDates(List<Element> visibleDates, int year) {
        List<LocalDate> dates = new ArrayList<>();

        Integer lastMonth = null;
        for (Element date : visibleDates) {

            int day = Integer.parseInt(date.select("span[class^='FlightOffersList__dateDay__']").text());
            String monthName = date.select("span[class^='FlightOffersList__dateShortMonth__']").text();
            int month = FinnishMonth.getMonthNumber(monthName);

            //check if year changes, basically Dec -> Jan
            if (lastMonth != null && month < lastMonth) {
                year += 1;
            }
            dates.add(LocalDate.of(year, month, day));
            lastMonth = month;
        }

        return dates;
    }

    private List<Element> getVisibleDateElements(Document priceMatrix) {
        return priceMatrix.select("time[aria-hidden='false']");
    }

    private int parseYear(Document priceMatrix) {
        String dateWithYear = priceMatrix.select("dt:contains(Menomatka)").next().text();
        return Integer.parseInt(dateWithYear.split("\\.")[2].split(",")[0]);
    }
}
