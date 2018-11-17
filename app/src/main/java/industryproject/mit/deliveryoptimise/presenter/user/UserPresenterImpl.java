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
    private IUserView iUserView;//View object
    private IUser iUser;//Model object
    private Context context;

    public UserPresenterImpl(Context context, IUserView iLoginView){
        this.iUserView = iLoginView;
        this.context = context;
        iUser = new UserModel(context, iLoginView);
    }

    @Override
    public void doLogin(String username, String password) {
        iUser.login(username, password);
    }

    @Override
    public void doRegister(UserInfo userInfo) {
        iUser.register(userInfo);
    }

    @Override
    public void loginResult(LoginResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iUserView.loginResult(null, "Login failed");
        }else{
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()) {
                iUserView.loginResult(response.getUserInfo().get(0), response.getMessage());
            }else {
                iUserView.loginResult(null, response.getMessage());
            }
        }
    }

    @Override
    public void registerResult(RegisterResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iUserView.registerResult(false, "Register failed");
        }else {
            iUserView.registerResult(response.getResult(), response.getMessage());
        }
    }

    @Override
    public void editUserInfo(UserInfo userInfo) {
        iUser.editUserInfo(userInfo);
    }

    @Override
    public void editResult(LoginResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iUserView.loginResult(null, "User information edits failed");
        }else {
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()) {
                iUserView.editUserInfoResult(response.getUserInfo().get(0), response.getMessage());
            } else {
                iUserView.editUserInfoResult(null, response.getMessage());
            }
        }
    }
}
