package ru.vsu.aviatickets.ticketssearch.api.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.KiwiResponse;

public interface KiwiAPI {
    @GET("/flights?vehicle_type=aircraft&v=3&curr=RUB&partner=picky&locale=ru")
    Call<KiwiResponse> getTickets(@Query("fly_from") String flyFrom, @Query("fly_to") String flyTo, @Query("date_from") String dateFrom, @Query("date_to") String dateTo, @Query("return_from") String returnFrom, @Query("return_to") String returnTo, @Query("flight_type") String flightType);
}
