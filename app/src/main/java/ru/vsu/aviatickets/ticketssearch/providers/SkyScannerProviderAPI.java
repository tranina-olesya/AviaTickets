package ru.vsu.aviatickets.ticketssearch.providers;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.ticketssearch.api.interfaces.SkyScannerAPI;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.models.skyscanner.SkyScannerResponse;

public class SkyScannerProviderAPI extends ProviderAPI<SkyScannerAPI> {
    public SkyScannerProviderAPI() {
        baseUrl = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com";
        init();
    }

    @Override
    protected SkyScannerAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(SkyScannerAPI.class);
    }

    @Override
    public List<Trip> sortTickets() {
        return null;
    }

    @Override
    public void getTickets(final TicketsCallback callback) {
        getSessionKey(new SessionKeyCallback() {
            @Override
            public void onGet(String sessionKey) {
                getTicketsApi().pollSessionResults(sessionKey, 0, 10, null, null, null).enqueue(new Callback<SkyScannerResponse>() {
                    @Override
                    public void onResponse(Call<SkyScannerResponse> call, Response<SkyScannerResponse> response) {
                        SkyScannerResponse body = response.body();
                    }

                    @Override
                    public void onFailure(Call<SkyScannerResponse> call, Throwable t) {
                        callback.onFail();
                    }
                });
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void getSessionKey(final SessionKeyCallback callback) {
        getTicketsApi().createSession("2019-04-08", "2019-04-10", "business", 0, 0, "RU", "RUB", "ru-RU", "VOZ-sky", "MOSC-sky", 1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (callback != null) {
                    String key = response.headers().get("location");
                    if (key != null) {
                        callback.onGet(key.substring(key.lastIndexOf("/")));
                    } else
                        callback.onFail();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }
}
