package ru.vsu.aviatickets.ticketssearch.providers;

import java.util.List;

import ru.vsu.aviatickets.ticketssearch.models.SearchData;
import ru.vsu.aviatickets.ticketssearch.models.Trip;

public interface TicketProviderApi {
    void getTickets(SearchData searchData, TicketsCallback callback);

    List<Trip> sortTickets();

    interface TicketsCallback {
        void onGet(List<Trip> trips);

        void onFail();
    }

    interface SessionKeyCallback {
        void onGet(String sessionKey);

        void onFail();
    }

    interface CityCallback {
        void onGet(String originCode, String destinationCode);

        void onFail();
    }
}
