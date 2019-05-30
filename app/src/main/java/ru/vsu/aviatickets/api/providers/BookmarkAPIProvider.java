package ru.vsu.aviatickets.api.providers;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.App;
import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.BookmarkRoute;
import ru.vsu.aviatickets.api.entities.dto.request.BookmarkRequestDTO;
import ru.vsu.aviatickets.api.interfaces.BookmarkAPI;

public class BookmarkAPIProvider extends ProviderAPI<BookmarkAPI> {

    @Override
    protected BookmarkAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(BookmarkAPI.class);
    }

    public void getBookmarks(BookmarksCallback callback) {
        getApi().getBookmarks(App.getUserCode()).enqueue(new Callback<List<BookmarkRoute>>() {
            @Override
            public void onResponse(Call<List<BookmarkRoute>> call, Response<List<BookmarkRoute>> response) {
                if (callback != null) {
                    List<BookmarkRoute> body = response.body();
                    if (body != null) {
                        callback.onComplete(body);
                    } else
                        callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<BookmarkRoute>> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public void deleteBookmark(Long bookmarkId, CompleteCallback callback) {
        getApi().deleteBookmark(bookmarkId).enqueue(new Callback<ResponseBody>() {
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

    public void addBookmark(BookmarkRoute bookmarkRoute, CompleteCallback callback) {
        BookmarkRequestDTO bookmarkRequestDTO = new BookmarkRequestDTO(App.getUserCode(), bookmarkRoute.getOrigin(),
                bookmarkRoute.getDestination(), bookmarkRoute.getAdultCount(), bookmarkRoute.getChildCount(),
                bookmarkRoute.getInfantCount(), bookmarkRoute.getFlightType(), bookmarkRoute.isTransfers(),
                bookmarkRoute.getClassType());

        getApi().createBookmark(bookmarkRequestDTO).enqueue(new Callback<Long>() {
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

    public void findBookmark(BookmarkRequestDTO bookmarkRequestDTO, BookmarkCallback callback) {
        getApi().findBookmark(bookmarkRequestDTO.getUserCode(), bookmarkRequestDTO.getOrigin(), bookmarkRequestDTO.getDestination(),
                bookmarkRequestDTO.getAdultCount(), bookmarkRequestDTO.getChildCount(), bookmarkRequestDTO.getInfantCount(),
                bookmarkRequestDTO.getFlightType(), bookmarkRequestDTO.getClassType(), bookmarkRequestDTO.getTransfers()).enqueue(new Callback<BookmarkRoute>() {
            @Override
            public void onResponse(Call<BookmarkRoute> call, Response<BookmarkRoute> response) {
                if (callback != null) {
                    BookmarkRoute body = response.body();
                    if (body != null && !body.checkAllNull())
                        callback.onComplete(body);
                    else
                        callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<BookmarkRoute> call, Throwable t) {
                if (callback != null)
                    callback.onFail();
            }
        });
    }

    public interface BookmarksCallback {
        void onComplete(List<BookmarkRoute> bookmarkRoutes);

        void onFail();
    }

    public interface BookmarkCallback {
        void onComplete(BookmarkRoute bookmarkRoute);

        void onFail();
    }
}
