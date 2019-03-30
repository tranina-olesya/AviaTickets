package ru.vsu.aviatickets.api.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vsu.aviatickets.api.model.Route;

public interface IATACodeAPI {
    @GET("/widgets_suggest_params")
    Call<Route> getRoute(@Query("q") String q);


}
