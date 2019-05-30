package ru.vsu.aviatickets.api.providers;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;
import ru.vsu.aviatickets.api.entities.dto.request.SearchHistoryEntryRequestDTO;
import ru.vsu.aviatickets.api.interfaces.SearchHistoryAPI;


public class SearchHistoryAPIProvider extends ProviderAPI<SearchHistoryAPI> {
    @Override
    protected SearchHistoryAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(SearchHistoryAPI.class);
    }

    public void getSearchHistory(String userCode, SearchHistoryCallback callback) {
        getApi().getSearchHistory(userCode).enqueue(new Callback<List<SearchHistoryEntry>>() {
            @Override
            public void onResponse(Call<List<SearchHistoryEntry>> call, Response<List<SearchHistoryEntry>> response) {
                if (callback != null) {
                    List<SearchHistoryEntry> body = response.body();

                    if (body != null) {
                        callback.onLoad(body);
                    } else
                        callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<SearchHistoryEntry>> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public void deleteSearchHistoryEntry(String userCode, Long searchHistoryEntryId, CompleteCallback callback) {
        getApi().deleteSearchHistoryEntry(userCode, searchHistoryEntryId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (callback != null)
                    callback.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public void deleteAllSearchHistory(String userCode, CompleteCallback callback) {
        getApi().deleteAllSearchHistory(userCode).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (callback != null)
                    callback.onComplete();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public void addSearchHistoryEntry(String userCode, SearchHistoryEntry searchHistoryEntry, CompleteCallback callback) {
        SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO = new SearchHistoryEntryRequestDTO(userCode,
                searchHistoryEntry.getOrigin(), searchHistoryEntry.getDestination(), searchHistoryEntry.getOutboundDate(),
                searchHistoryEntry.getInboundDate(), searchHistoryEntry.getAdultsCount(), searchHistoryEntry.getChildrenCount(),
                searchHistoryEntry.getInfantsCount(), searchHistoryEntry.getFlightType(), searchHistoryEntry.getTransfers(),
                searchHistoryEntry.getCabinClass());

        getApi().addSearchHistoryEntry(searchHistoryEntryRequestDTO).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (callback != null)
                    callback.onComplete();
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public interface SearchHistoryCallback {
        void onLoad(List<SearchHistoryEntry> searchHistoryEntries);

        void onFail();
    }
}
