package com.roko.hotelpricefollower.scraper.parser;

import com.roko.hotelpricefollower.scraper.parser.dto.ParsedRoomPrice;
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
        return priceMatrix.select("tr[class^='RoomPriceRow__roomPriceRow__']");
    }

    private Map<String, List<ParsedRoomPrice>> roomNamesAndPrices(Elements roomElements) {
        Map<String, List<ParsedRoomPrice>> roomNameAndPrices = new LinkedHashMap<>();

        for (Element roomElement : roomElements) {
            String roomName = roomElement.select("th[class^='RoomPriceRow__roomHeader__']").text();
            List<ParsedRoomPrice> parsedRoomPrices = this.parseRoomPrices(roomElement);

            roomNameAndPrices.put(roomName, parsedRoomPrices);
        }


        return roomNameAndPrices;
    }

    private List<ParsedRoomPrice> parseRoomPrices(Element roomElement) {
        Elements priceElements = roomElement.select("td[class^='RoomPriceRow__priceOffer__']");

        List<ParsedRoomPrice> roomPrices = new ArrayList<>();
        for (Element priceElement : priceElements) {
            ParsedRoomPrice parsedRoomPrice = parseSingleRoomPrice(priceElement);
            roomPrices.add(parsedRoomPrice);
        }

        return roomPrices;
    }

    private ParsedRoomPrice parseSingleRoomPrice(Element priceElement) {
        ParsedRoomPrice roomPrice = new ParsedRoomPrice();

        //Check if room is sold out, otherwise parse price
        if (isSoldOut(priceElement.text())) {
            roomPrice.setSoldOut(true);
        } else {
            String priceString = priceElement.select("span[class^='RoomPriceButton__text__']").text();
            Long price = Long.parseLong(priceString.split(",")[0].replace(" ", ""));
            roomPrice.setPrice(price);
        }

        //Check if price contains additional information
        String possibleAdditionalInfo = priceElement.select("span[class*='RoomPriceButton__tag__']").text();
        if (hasAdditionalInfo(possibleAdditionalInfo)) {
            roomPrice.setAdditionalInformation(possibleAdditionalInfo);
        }

        return roomPrice;
    }

    private boolean isSoldOut(String priceElement) {
        return priceElement.equalsIgnoreCase("Loppuunmyyty");
    }

    private boolean hasAdditionalInfo(String possibleAdditionalInfo) {
        return !possibleAdditionalInfo.isEmpty();
    }

}