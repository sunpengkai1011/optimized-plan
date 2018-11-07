package industryproject.mit.deliveryoptimise.network;

import android.content.Context;

import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.entities.network.LoginResponse;
import industryproject.mit.deliveryoptimise.network.service.UserService;
import io.reactivex.Single;

/**
 * The request of editing the user information.
 */
public class UserInfoEditRequest extends BaseRequest<UserService, LoginResponse, LoginResponse> {
    private UserInfo userInfo;

    public UserInfoEditRequest(Context context, UserInfo userInfo) {
        super(context);
        this.userInfo = userInfo;
    }

    @Override
    protected Map<String, String> getHeader() {
        return null;
    }

    @Override
    protected Class<UserService> getEndpointClass() {
        return UserService.class;
    }

    @Override
    protected Single<LoginResponse> getEndpoint(UserService endpoint) {
        return endpoint.userEdit(userInfo);
    }

    @Override
    protected LoginResponse dealWithResponse(LoginResponse response) {
        return response;
    }
}
