package ru.vsu.aviatickets.api.providers;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.vsu.aviatickets.api.CompleteCallback;
import ru.vsu.aviatickets.api.entities.dto.request.UserRequestDTO;
import ru.vsu.aviatickets.api.interfaces.UserAPI;

public class UserAPIProvider extends ProviderAPI<UserAPI> {
    @Override
    protected UserAPI createApiClass(Retrofit retrofit) {
        return retrofit.create(UserAPI.class);
    }

    public void createUser(String userCode, CompleteCallback callback) {
        getApi().createUser(new UserRequestDTO(userCode)).enqueue(new Callback<ResponseBody>() {
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
}
