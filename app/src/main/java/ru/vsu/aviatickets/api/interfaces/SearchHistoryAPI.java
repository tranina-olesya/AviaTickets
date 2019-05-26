package ru.vsu.aviatickets.api.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.vsu.aviatickets.api.entities.dto.request.SearchHistoryEntryRequestDTO;
import ru.vsu.aviatickets.api.entities.SearchHistoryEntry;

public interface SearchHistoryAPI {

    @GET("/users/{userCode}/search-history")
    Call<List<SearchHistoryEntry>> getSearchHistory(@Path("userCode") String userCode);

    @POST("/search-history")
    Call<Long> addSearchHistoryEntry(@Body SearchHistoryEntryRequestDTO searchHistoryEntryRequestDTO);

    @DELETE("/search-history/{userCode}/{searchHistoryEntryId}")
    Call<ResponseBody> deleteSearchHistoryEntry(@Path("userCode") String userCode,
            @Path("searchHistoryEntryId") Long searchHistoryEntryId);

    @DELETE("/search-history/{userCode}")
    Call<ResponseBody> deleteAllSearchHistory(@Path("userCode") String userCode);
}
