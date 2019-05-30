package ru.vsu.aviatickets.api.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.vsu.aviatickets.api.entities.dto.request.UserRequestDTO;

public interface UserAPI {
    @POST("/users")
    Call<ResponseBody> createUser(@Body UserRequestDTO userRequestDTO);
}
