package ru.vsu.aviatickets.ticketssearch.api.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerCities;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerResponse;

public interface SkyScannerAPI {
    @FormUrlEncoded
    @Headers({"X-RapidAPI-Key: 5218bbef51msh474a4c05a0b5196p1fd9c4jsn18c6c2615b8d",
            "X-RapidAPI-Host: skyscanner-skyscanner-flight-search-v1.p.rapidapi.com"})
    @POST("/apiservices/pricing/v1.0")
    Call<ResponseBody> createSession(@Field("outboundDate") String outboundDate, @Field("inboundDate") String inboundDate,
                                     @Field("cabinClass") String cabinClass,
                                     @Field("adults") int adults, @Field("children") int children, @Field("infants") int infants,
                                     @Field("country") String countryCode, @Field("currency") String currency, @Field("locale") String locale, @Field("originPlace") String originPlace,
                                     @Field("destinationPlace") String destinationPlace, @Field("groupPricing") boolean groupPricing);

    @Headers({"X-RapidAPI-Key: 5218bbef51msh474a4c05a0b5196p1fd9c4jsn18c6c2615b8d"})
    @GET("/apiservices/autosuggest/v1.0/RU/RUB/ru-RU/")
    Call<SkyScannerCities> listPlaces(@Query("query") String query);

    @Headers({"X-RapidAPI-Key: 5218bbef51msh474a4c05a0b5196p1fd9c4jsn18c6c2615b8d",
            "X-RapidAPI-Host: skyscanner-skyscanner-flight-search-v1.p.rapidapi.com"})
    @GET("/apiservices/pricing/uk2/v1.0/{sessionkey}")
    Call<SkyScannerResponse> pollSessionResults(@Path("sessionkey") String sessionkey, @Query("pageIndex") Integer pageIndex, @Query("pageSize") Integer pageSize,
                                                @Query("sortType") String sortType, @Query("sortOrder") String sortOrder, @Query("duration") Integer duration,
                                                @Query("stops") Integer stops);


}
