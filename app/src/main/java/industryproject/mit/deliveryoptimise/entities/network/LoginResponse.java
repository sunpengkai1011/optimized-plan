package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

/**
 * The login response entity.
 */
public class LoginResponse extends BaseResponse implements Serializable{
    private List<UserInfo> userInfo;

    public List<UserInfo> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }
}
