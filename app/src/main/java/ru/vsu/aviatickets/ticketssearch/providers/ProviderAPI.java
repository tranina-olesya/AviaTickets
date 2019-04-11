package ru.vsu.aviatickets.ticketssearch.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.aviatickets.BuildConfig;
import ru.vsu.aviatickets.ticketssearch.models.Trip;

public abstract class ProviderAPI<T> {
    private T ticketsApi;
    private String baseUrl;

    public ProviderAPI(String baseUrl){
        this.baseUrl = baseUrl;
        init();
    }

    protected void init(){
        ticketsApi = createApiClass(buildRetrofit(buildOkHttp()));
    }

    protected T getTicketsApi(){
        return ticketsApi;
    }

    public abstract void getTickets(SkyScannerProviderAPI.TicketsCallback callback);

    public abstract List<Trip> sortTickets();

    private OkHttpClient buildOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT);
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
        }
        return builder.build();

    }

    private Retrofit buildRetrofit(OkHttpClient okHttpClient) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    protected abstract T createApiClass(Retrofit retrofit);

    public interface TicketsCallback {
        void onGet(List<Trip> trips);
        void onFail();
    }

    protected interface SessionKeyCallback {
        void onGet(String sessionKey);
        void onFail();
    }
}
