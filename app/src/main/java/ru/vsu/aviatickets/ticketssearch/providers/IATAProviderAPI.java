package ru.vsu.aviatickets.ticketssearch.providers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.ticketssearch.api.interfaces.IATACodeAPI;
import ru.vsu.aviatickets.ticketssearch.models.iata.Route;

public class IATAProviderAPI extends ProviderAPI<IATACodeAPI> {
    public IATAProviderAPI() {
        super("https://www.travelpayouts.com");
    }

    @Override
    protected IATACodeAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(IATACodeAPI.class);
    }

    public void getCityCodes(String origin, String destination, TicketProviderApi.CityCallback callback) {
        String query = String.format("Из %s в %s", origin, destination);
        getApi().getRoute(query).enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                if (response.body() != null && response.body().getOrigin() != null && response.body().getOrigin() != null) {
                    Route route = response.body();
                    callback.onGet(route.getOrigin().getIata(), route.getDestination().getIata());
                } else {
                    callback.onFail(APIError.CITY_NOT_FOUND);
                }
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                callback.onFail(APIError.NO_RESPONSE);
            }
        });
    }
}
