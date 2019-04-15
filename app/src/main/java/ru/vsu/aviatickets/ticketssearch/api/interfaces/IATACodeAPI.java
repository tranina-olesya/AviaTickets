package ru.vsu.aviatickets.ticketssearch.api.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vsu.aviatickets.ticketssearch.models.iata.Route;

public interface IATACodeAPI {
    @GET("/widgets_suggest_params")
    Call<Route> getRoute(@Query("q") String q);


}
