package ru.vsu.aviatickets.ticketssearch.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ru.vsu.aviatickets.api.entities.tripmodels.Flight;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;

public class SortTrips {

    public static void sortTrips(List<Trip> trips, SortFilterType sortFilterType) {
        switch (sortFilterType) {
            case MIN_PRICE:
                sortByMinPrice(trips);
                break;
            case MAX_PRICE:
                sortByMaxPrice(trips);
                break;
            case MIN_TIME:
                sortByMinTime(trips);
                break;
            case MAX_TIME:
                sortByMaxTime(trips);
                break;
            case MIN_TRANSFERS:
                sortByMinTransfers(trips);
                break;
            case MAX_TRANSFERS:
                sortByMaxTransfers(trips);
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
                return trip2.getMinPrice().compareTo(trip1.getMinPrice());
            }
        });
    }

    private static void sortByMinTime(List<Trip> trips) {
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return sumTime(trip1) - sumTime(trip2);
            }
        });
    }

    private static void sortByMaxTime(List<Trip> trips) {
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return sumTime(trip2) - sumTime(trip1);
            }
        });
    }

    private static void sortByMinTransfers(List<Trip> trips) {
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return countTransfers(trip1) - countTransfers(trip2);
            }
        });
    }

    private static void sortByMaxTransfers(List<Trip> trips) {
        Collections.sort(trips, new Comparator<Trip>() {
            @Override
            public int compare(Trip trip1, Trip trip2) {
                return countTransfers(trip2) - countTransfers(trip1);
            }
        });
    }

    private static int sumTime(Trip trip) {
        int time = 0;

        Flight outbound = trip.getOutbound();
        if (outbound != null)
            time += outbound.getDuration();
        Flight inbound = trip.getInbound();
        if (inbound != null)
            time += inbound.getDuration();

        return time;
    }

    private static int countTransfers(Trip trip) {
        int transfersCount = 0;

        Flight outbound = trip.getOutbound();
        if (outbound != null)
            transfersCount += outbound.getFlightParts().size();
        Flight inbound = trip.getInbound();
        if (inbound != null)
            transfersCount += inbound.getFlightParts().size();

        return transfersCount;
    }
}
