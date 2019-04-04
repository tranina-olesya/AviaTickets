package ru.vsu.aviatickets.ticketssearch.providers;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.aviatickets.BuildConfig;
import ru.vsu.aviatickets.ticketssearch.models.Ticket;

public abstract class ProviderAPI<T> {
    private T ticketsApi;
    protected String baseUrl;

    public ProviderAPI(){
    }

    protected void init(){
        ticketsApi = createApiClass(buildRetrofit(buildOkHttp()));
    }
    protected T getTicketsApi(){
        return ticketsApi;
    }

    public abstract List<Ticket> getTickets();

    public abstract List<Ticket> sortTickets();

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
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    protected abstract T createApiClass(Retrofit retrofit);

}
