package ru.vsu.aviatickets.api;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.aviatickets.BuildConfig;

public class ApiService {
    private static ApiCityService apiCityService;
    private static final String BASE_URL = "https://www.travelpayouts.com";

    public static ApiCityService createApi() {
        if(apiCityService == null){
            apiCityService = createService(buildRetrofit(buildOkHttp()));
        }
        return apiCityService;
    }

    private static OkHttpClient buildOkHttp() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        /*HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(httpLoggingInterceptor);
        }*/
        return clientBuilder.build();
    }

    private static Retrofit buildRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static ApiCityService createService(Retrofit retrofit){
        return retrofit.create(ApiCityService.class);
    }
}
