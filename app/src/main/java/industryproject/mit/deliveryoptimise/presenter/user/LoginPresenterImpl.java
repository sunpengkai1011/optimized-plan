package industryproject.mit.deliveryoptimise.presenter.user;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.network.LoginResponse;
import industryproject.mit.deliveryoptimise.entities.network.RegisterResponse;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.model.user.IUserLogin;
import industryproject.mit.deliveryoptimise.model.user.UserLoginModel;
import industryproject.mit.deliveryoptimise.view.user.ILoginView;


/**
 * The implementation of "ILoginPresenter"
 */
public class LoginPresenterImpl implements ILoginPresenter {
    private ILoginView iLoginView;//View object
    private IUserLogin iUserLogin;//Model object
    private Context context;

    public LoginPresenterImpl(Context context, ILoginView iLoginView){
        this.iLoginView = iLoginView;
        this.context = context;
        iUserLogin = new UserLoginModel(context, iLoginView);
    }

    @Override
    public void doLogin(String username, String password) {
        iUserLogin.login(username, password);
    }

    @Override
    public void doRegister(UserInfo userInfo) {
        iUserLogin.register(userInfo);
    }

    @Override
    public void loginResult(LoginResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iLoginView.loginResult(null, "Login failed");
        }else{
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()) {
                iLoginView.loginResult(response.getUserInfo().get(0), response.getMessage());
            }else {
                iLoginView.loginResult(null, response.getMessage());
            }
        }
    }

    @Override
    public void registerResult(RegisterResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iLoginView.loginResult(null, "Register failed");
        }else {
            iLoginView.registerResult(response.getResult(), response.getMessage());
        }
    }

    @Override
    public void editUserInfo(UserInfo userInfo) {
        iUserLogin.editUserInfo(userInfo);
    }

    @Override
    public void editResult(LoginResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iLoginView.loginResult(null, "User information edits failed");
        }else {
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()) {
                iLoginView.editUserInfoResult(response.getUserInfo().get(0), response.getMessage());
            } else {
                iLoginView.editUserInfoResult(null, response.getMessage());
            }
        }
    }
}
