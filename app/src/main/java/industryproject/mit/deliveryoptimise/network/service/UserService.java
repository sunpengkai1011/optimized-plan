package industryproject.mit.deliveryoptimise.network.service;

import industryproject.mit.deliveryoptimise.entities.network.LoginResponse;
import industryproject.mit.deliveryoptimise.entities.network.RegisterResponse;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("user/{username}")
    Single<LoginResponse> signin(@Path("username") String username, @Header("Authorization") String authorization);

    @POST("user")
    Single<RegisterResponse> register(@Body UserInfo userInfo);

    @POST("edit")
    Single<LoginResponse> userEdit(@Body UserInfo userInfo);
}
