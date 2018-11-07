package industryproject.mit.deliveryoptimise.network;

import android.content.Context;

import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.network.RegisterResponse;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.network.service.UserService;
import io.reactivex.Single;

public class RegisterRequest extends BaseRequest<UserService, RegisterResponse, RegisterResponse> {
    private UserInfo userInfo;

    public RegisterRequest(Context context, UserInfo userInfo) {
        super(context);
        this.userInfo = userInfo;
    }

    @Override
    protected Map<String, String> getHeader() {
        return null;
    }

    @Override
    protected Class getEndpointClass() {
        return UserService.class;
    }

    @Override
    protected Single<RegisterResponse> getEndpoint(UserService endpoint) {
        return endpoint.register(userInfo);
    }

    @Override
    protected RegisterResponse dealWithResponse(RegisterResponse response) {
        return response;
    }
}