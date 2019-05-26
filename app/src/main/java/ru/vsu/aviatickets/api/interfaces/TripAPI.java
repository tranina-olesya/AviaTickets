package ru.vsu.aviatickets.api.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;
import ru.vsu.aviatickets.api.entities.tripmodels.iata.Route;

public interface TripAPI {
    @GET("/cities")
    Call<Route> gerCityCodes(@Query("origin") String origin, @Query("destination") String destination);

    @GET("/trips")
    Call<List<Trip>> getTrips(@Query("originCode") String originCode, @Query("originName") String originName,
                        @Query("destinationCode") String destinationCode, @Query("destinationName") String destinationName,
                        @Query("dateOutbound") String dateOutbound, @Query("dateInbound") String dateInbound,
                        @Query("adultsCount") Integer adultsCount, @Query("childrenCount") Integer childrenCount,
                        @Query("infantsCount") Integer infantsCount, @Query("flightType") FlightType flightType,
                        @Query("cabinClass") CabinClass cabinClass, @Query("transfers") Boolean transfers);
}
