package ru.vsu.aviatickets.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vsu.aviatickets.api.model.Route;

public interface ApiCityService {
    @GET("/widgets_suggest_params")
    public Call<Route> getRoute(@Query("q") String q);


}
