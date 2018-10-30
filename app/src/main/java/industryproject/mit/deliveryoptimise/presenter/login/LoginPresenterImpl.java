package industryproject.mit.deliveryoptimise.presenter.login;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.model.user.IUserLogin;
import industryproject.mit.deliveryoptimise.model.user.UserLoginModel;
import industryproject.mit.deliveryoptimise.view.login.ILoginView;


public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginView iLoginView;
    private IUserLogin iUserLogin;
    private UserInfo userInfo;
    private Context context;

    public LoginPresenterImpl(Context context, ILoginView iLoginView){
        this.iLoginView = iLoginView;
        this.context = context;
    }

    @Override
    public void doLogin(String username, String password) {
        iUserLogin = new UserLoginModel(context);
        if (iUserLogin.login(username, password)){
            userInfo = iUserLogin.getUserInfo(username);
            iLoginView.loginResult(userInfo, Constants.RESPONSE_CODE_SUCCESSFUL);
        }else {
            iLoginView.loginResult(new UserInfo(), Constants.RESPONSE_CODE_FAIL);
        }
    }

    @Override
    public void doRegister(UserInfo userInfo) {
        if (userInfo != null) {
            iUserLogin = new UserLoginModel(context);
            if (iUserLogin.register(userInfo)) {
                iLoginView.registerResult(iUserLogin.getUserInfo(userInfo.getUserName()), Constants.RESPONSE_CODE_SUCCESSFUL);
            } else {
                iLoginView.registerResult(userInfo, Constants.RESPONSE_CODE_FAIL);
            }
        }else{
            iLoginView.registerResult(new UserInfo(), Constants.RESPONSE_CODE_FAIL);
        }
    }
}
