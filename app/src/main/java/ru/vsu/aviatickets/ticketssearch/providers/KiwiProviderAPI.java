package ru.vsu.aviatickets.ticketssearch.providers;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.ticketssearch.api.interfaces.KiwiAPI;
import ru.vsu.aviatickets.ticketssearch.models.Trip;
import ru.vsu.aviatickets.ticketssearch.models.kiwi.KiwiResponse;

public class KiwiProviderAPI extends ProviderAPI<KiwiAPI> {
    public KiwiProviderAPI() {
        baseUrl = "https://api.skypicker.com";
        init();
    }

    @Override
    public void getTickets(TicketsCallback callback) {
        getTicketsApi().getTickets("VOZ", "MOW", "18/04/2019", "18/04/2019", "20/04/2019", "20/04/2019","oneway").enqueue(new Callback<KiwiResponse>() {
            @Override
            public void onResponse(Call<KiwiResponse> call, Response<KiwiResponse> response) {

            }

            @Override
            public void onFailure(Call<KiwiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public List<Trip> sortTickets() {
        return null;
    }

    @Override
    protected KiwiAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(KiwiAPI.class);
    }
}
