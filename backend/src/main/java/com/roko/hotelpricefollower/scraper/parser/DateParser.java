package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.FinnishMonth;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

            int day = Integer.parseInt(date.getElementsByClass("tcne-pm-flightoffer__item-date-left").text());
            String monthName = date.getElementsByClass("tcne-pm-flightoffer__item-date-right-day").text();
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
        Elements allDates = priceMatrix.getElementsByClass("tcne-pm-flightoffer");

        //Remove non-visible elements
        return allDates.stream()
                .filter(date ->
                        !date.attr("class").contains("tcne-pm-flightoffer--disabled"))
                .toList();
    }

    private int parseYear(Document priceMatrix) {
        Elements elements = priceMatrix.getElementsByClass("tcne-pm-price-detail__row");

        for (Element element : elements) {
            String title = element.getElementsByClass("tcne-pm-price-detail-row__title").text();

            if (title.equals("Menomatka")) {
                //Example: Su 22.3.2026, 7:15
                String dateWithYear = element.getElementsByTag("div").get(2).text();
                //Result: 2026
                return Integer.parseInt(dateWithYear.split("\\.")[2].split(",")[0]);
            }
        }

        //Return current year if parsing fails
        return LocalDate.now().getYear();
    }
}
