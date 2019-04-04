package ru.vsu.aviatickets.api.interfaces.city;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.vsu.aviatickets.api.model.SkyScannerPlace;
import ru.vsu.aviatickets.api.model.SkyScannerPlaces;

public interface SkyScannerAPI {
    @FormUrlEncoded
    @POST("/apiservices/pricing/v1.0")
    Call<Object> createSession(@Header("X-RapidAPI-Key") String xRapidAPIKey, @Header("Content-Type") String contentType,
                                      @Field("inboundDate") String inboundDate, @Field("cabinClass") String cabinClass,
                                      @Field("children") int children, @Field("infants") int infants, @Field("country") String countryCode,
                                      @Field("currency") String currency, @Field("locale") String locale, @Field("originPlace") String originPlace,
                                      @Field("destinationPlace") String destinationPlace, @Field("outboundDate") String outboundDate,
                                      @Field("adults") int adults);

    @Headers({"X-RapidAPI-Key: 5218bbef51msh474a4c05a0b5196p1fd9c4jsn18c6c2615b8d"})
    @GET("/apiservices/autosuggest/v1.0/RU/RUB/ru-RU/")
    Call<SkyScannerPlaces> listPlaces(@Query("query") String query);
}
