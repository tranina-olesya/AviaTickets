package ru.vsu.aviatickets.api.providers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.api.entities.tripmodels.SearchData;
import ru.vsu.aviatickets.api.entities.tripmodels.Trip;
import ru.vsu.aviatickets.api.entities.tripmodels.iata.Route;
import ru.vsu.aviatickets.api.interfaces.TripAPI;

public class TripAPIProvider extends ProviderAPI<TripAPI> {

    @Override
    protected TripAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(TripAPI.class);
    }

    public void getCityCode(String origin, String destination, CityCallback callback) {
        getApi().gerCityCodes(origin, destination).enqueue(new Callback<Route>() {
            @Override
            public void onResponse(Call<Route> call, Response<Route> response) {
                if (callback != null) {
                    Route body = response.body();
                    if (body != null && body.getOrigin() != null && body.getDestination() != null)
                        callback.onComplete(body.getOrigin().getIata(), body.getDestination().getIata());
                    else
                        callback.onFail(APIError.CITY_NOT_FOUND);
                }
            }

            @Override
            public void onFailure(Call<Route> call, Throwable t) {
                callback.onFail(APIError.NO_RESPONSE);
            }
        });
    }

    public void getTrips(SearchData searchData, TripsCallback callback) {
        getApi().getTrips(searchData.getOrigin().getCode(), searchData.getOrigin().getName(),
                searchData.getDestination().getCode(), searchData.getDestination().getName(),
                convertDate(searchData.getOutboundDate()), convertDate(searchData.getInboundDate()),
                searchData.getAdultsCount(), searchData.getChildrenCount(), searchData.getInfantsCount(),
                searchData.getFlightType(), searchData.getCabinClass(), searchData.getTransfers()).enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                if (callback != null) {
                    List<Trip> body = response.body();
                    if (body != null)
                        callback.onComplete(body);
                    else
                        callback.onFail(APIError.TICKETS_NOT_FOUND);
                }
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                if (callback != null)
                    callback.onFail(APIError.NO_RESPONSE);
            }
        });
    }

    public interface CityCallback {
        void onComplete(String origin, String destination);

        void onFail(APIError error);
    }

    public interface TripsCallback {
        void onComplete(List<Trip> trips);

        void onFail(APIError error);
    }

    private String convertDate(Date date) {
        if (date == null)
            return "";
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        return simpleDateFormat.format(date);
    }
}
