package com.roko.hotelpricefollower.scraper.parser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoomPriceParser {

    protected Map<String, List<ParsedRoomPrice>> parseRoomsAndPrices(Document priceMatrix) {
        //Get roomElements from priceMatrix
        Elements roomElements = this.roomElements(priceMatrix);

        return this.roomNamesAndPrices(roomElements);
    }

    private Elements roomElements(Document priceMatrix) {
        return priceMatrix.getElementsByClass("tcne-pm-roomoffers-row");
    }

    private Map<String, List<ParsedRoomPrice>> roomNamesAndPrices(Elements roomElements) {
        Map<String, List<ParsedRoomPrice>> roomNameAndPrices = new LinkedHashMap<>();

        for (Element roomElement : roomElements) {
            String roomName = roomElement.getElementsByClass("tcne-pm-roomoffers-name").text();
            List<ParsedRoomPrice> parsedRoomPrices = this.parseRoomPrices(roomElement);

            roomNameAndPrices.put(roomName, parsedRoomPrices);
        }


        return roomNameAndPrices;
    }

    private List<ParsedRoomPrice> parseRoomPrices(Element roomElement) {
        Elements priceElements = roomElement.getElementsByClass("tcne-pm-price-cell-container");

        List<ParsedRoomPrice> roomPrices = new ArrayList<>();
        for (Element priceElement : priceElements) {
            ParsedRoomPrice roomPrice = new ParsedRoomPrice();

            //Check if room is sold out, otherwise parse price
            String possiblePrice = priceElement.getElementsByClass("tcne-pm-price-cell__price").text();
            if (isSoldOut(possiblePrice)) {
                roomPrice.setSoldOut(true);
            } else {
                Long price = Long.parseLong(possiblePrice.split(",")[0].replace(" ", ""));
                roomPrice.setPrice(price);
            }

            //Check if price contains additional information
            String possibleAdditionalInfo = priceElement.getElementsByClass("tcne-pm-price-cell__label").text();
            if (hasAdditionalInfo(possibleAdditionalInfo)) {
                roomPrice.setAdditionalInformation(possibleAdditionalInfo);
            }

            roomPrices.add(roomPrice);
        }

        return roomPrices;
    }

    private boolean isSoldOut(String possiblePrice) {
        return possiblePrice.equalsIgnoreCase("Loppuunmyyty");
    }

    private boolean hasAdditionalInfo(String possibleAdditionalInfo) {
        return !possibleAdditionalInfo.isEmpty();
    }

    //Helper class
    public class ParsedRoomPrice {
        private Long price;
        private boolean soldOut;
        private String additionalInformation;

        public ParsedRoomPrice() {
            this.soldOut = false;
        }

        public Long getPrice() {
            return price;
        }

        public boolean isSoldOut() {
            return soldOut;
        }

        public String getAdditionalInformation() {
            return additionalInformation;
        }

        public void setAdditionalInformation(String additionalInformation) {
            this.additionalInformation = additionalInformation;
        }

        public void setSoldOut(boolean soldOut) {
            this.soldOut = soldOut;
        }

        public void setPrice(Long price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "ParsedRoomPrice{" +
                    "price=" + price +
                    ", soldOut=" + soldOut +
                    ", additionalInformation='" + additionalInformation + '\'' +
                    "}\n";
        }
    }
}


