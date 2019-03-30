package ru.vsu.aviatickets.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.aviatickets.BuildConfig;
import ru.vsu.aviatickets.api.interfaces.IATACodeAPI;
import ru.vsu.aviatickets.api.interfaces.SkyScannerAPI;

public class SkyScannerApiService {
    private static SkyScannerAPI skyScannerAPI;
    private static final String BASE_URL = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";

    public static SkyScannerAPI createApi() {
        if(skyScannerAPI == null){
            skyScannerAPI = createService(buildRetrofit(buildOkHttp()));
        }
        return skyScannerAPI;
    }

    private static OkHttpClient buildOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
        }
        return builder.build();

    }

    private static Retrofit buildRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static SkyScannerAPI createService(Retrofit retrofit){
        return retrofit.create(SkyScannerAPI.class);
    }
}
