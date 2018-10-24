package industryproject.mit.deliveryoptimise.model.user;

import android.content.Context;

import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;

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
}
