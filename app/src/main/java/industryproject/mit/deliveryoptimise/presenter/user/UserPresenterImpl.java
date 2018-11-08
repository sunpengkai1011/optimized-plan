package industryproject.mit.deliveryoptimise.presenter.user;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.network.LoginResponse;
import industryproject.mit.deliveryoptimise.entities.network.RegisterResponse;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.model.user.IUser;
import industryproject.mit.deliveryoptimise.model.user.UserModel;
import industryproject.mit.deliveryoptimise.view.user.IUserView;


/**
 * The implementation of "IUserPresenter"
 */
public class UserPresenterImpl implements IUserPresenter {
    private IUserView iLoginView;//View object
    private IUser iUserLogin;//Model object
    private Context context;

    public UserPresenterImpl(Context context, IUserView iLoginView){
        this.iLoginView = iLoginView;
        this.context = context;
        iUserLogin = new UserModel(context, iLoginView);
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
