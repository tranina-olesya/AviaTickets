package ru.vsu.aviatickets.ticketssearch.utils;

import java.util.List;

import ru.vsu.aviatickets.api.entities.tripmodels.PriceLink;

public class TripUtils {
    public static PriceLink getMinPriceLink(List<PriceLink> priceLinks) {
        if (priceLinks == null || priceLinks.isEmpty())
            return null;
        PriceLink result = priceLinks.get(0);
        for (int i = 1; i < priceLinks.size(); i++) {
            if (priceLinks.get(i).getPrice() < result.getPrice()) {
                result = priceLinks.get(i);
            }
        }
        return result;
    }
}
