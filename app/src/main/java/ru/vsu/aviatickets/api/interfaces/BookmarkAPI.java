package ru.vsu.aviatickets.api.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.dto.request.BookmarkRequestDTO;
import ru.vsu.aviatickets.api.entities.tripmodels.CabinClass;
import ru.vsu.aviatickets.api.entities.tripmodels.FlightType;

public interface BookmarkAPI {

    @GET("/users/{userCode}/bookmarks")
    Call<List<BookmarkRoute>> getBookmarks(@Path("userCode") String userCode);

    @POST("/bookmarks")
    Call<Long> createBookmark(@Body BookmarkRequestDTO bookmarkRequestDTO);

    @DELETE("/bookmarks/{userCode}/{bookmarkId}")
    Call<ResponseBody> deleteBookmark(@Path("userCode") String userCode, @Path("bookmarkId") Long bookmarkId);

    @GET("/bookmarks/find")
    Call<BookmarkRoute> findBookmark(@Query("userCode") String userCode, @Query("origin") String origin, @Query("destination") String destination,
                                     @Query("adultCount") Integer adultCount, @Query("childCount") Integer childrenCount,
                                     @Query("infantCount") Integer infantsCount, @Query("flightType") FlightType flightType,
                                     @Query("classType") CabinClass cabinClass, @Query("transfers") boolean transfers);
}
