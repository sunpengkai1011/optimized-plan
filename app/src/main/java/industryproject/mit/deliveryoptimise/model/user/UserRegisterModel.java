package industryproject.mit.deliveryoptimise.model.user;

import android.content.Context;

import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;

public class UserRegisterModel implements IUserRegister {
    private Context context;

    public UserRegisterModel(Context context){
        this.context = context;
    }

    @Override
    public UserInfo getUserInfo(String username) {
        return GeneralUtil.fromJson(GeneralUtil.getDataFromSP(context, username), UserInfo.class);
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
