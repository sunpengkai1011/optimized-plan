package industryproject.mit.deliveryoptimise.model.user;

import android.content.Context;

import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;

/**
 * It is the Model in MVP pattern. For operating the data about the map.
 */
public class UserLoginModel implements IUserLogin {
    private UserInfo userInfo;
    private Context context;

    public UserLoginModel(Context context){
        this.context = context;
    }

    @Override
    public UserInfo getUserInfo(String username) {
        userInfo = GeneralUtil.fromJson(GeneralUtil.getDataFromSP(context, username), UserInfo.class);
        return userInfo;
    }

    @Override
    public boolean login(String username, String password) {
        userInfo =  GeneralUtil.fromJson(GeneralUtil.getDataFromSP(context, username), UserInfo.class);
        if (userInfo != null) {
            if (username.equals(userInfo.getUserName()) && password.equals(userInfo.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean register(UserInfo userInfo) {
        if (fakeRegisterRequest(userInfo)){
            return true;
        }
        return false;
    }

    private boolean fakeRegisterRequest(UserInfo userInfo){
        if (userInfo != null){
            userInfo.setUserId(String.valueOf(System.currentTimeMillis()));
            GeneralUtil.storDataBySP(context, userInfo.getUserName(), GeneralUtil.toJson(userInfo, UserInfo.class));
            return true;
        }
        return false;
    }
}
