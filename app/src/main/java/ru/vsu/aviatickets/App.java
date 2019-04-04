package ru.vsu.aviatickets;

import android.app.Application;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.aviatickets.api.interfaces.city.IATACodeAPI;
import ru.vsu.aviatickets.api.interfaces.city.SkyScannerAPI;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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

    private static Retrofit buildRetrofit(OkHttpClient okHttpClient, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static <T> Object createApiClass(Retrofit retrofit, Class<T> ticketsApiClass) {
        return retrofit.create(ticketsApiClass);
    }

    private static <T> Object createApi(Class<T> tClass, String url) {
        return createApiClass(buildRetrofit(buildOkHttp(), url), tClass);
    }

    public static IATACodeAPI getIATACodeAPI() {
        return (IATACodeAPI) createApi(IATACodeAPI.class, "https://www.travelpayouts.com");
    }

    public static SkyScannerAPI getSkyScannerAPI() {
        return (SkyScannerAPI) createApi(SkyScannerAPI.class, "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
    }


}

