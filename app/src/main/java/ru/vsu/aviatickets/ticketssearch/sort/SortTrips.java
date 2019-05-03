package ru.vsu.aviatickets.ticketssearch.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.Trip;

public class SortTrips {

    public static void sortTrips(List<Trip> trips, SortFilterType sortFilterType) {
        switch (sortFilterType) {
            case MIN_PRICE:
                sortByMinPrice(trips);
                break;
            case MAX_PRICE:
                sortByMaxPrice(trips);
                break;
        }
    }

    private static void sortByMinPrice(List<Trip> trips) {
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return trip1.getMinPrice().compareTo(trip2.getMinPrice());
            }
        });
    }

    private static void sortByMaxPrice(List<Trip> trips) {
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return -trip1.getMinPrice().compareTo(trip2.getMinPrice());
            }
        });
    }
}
