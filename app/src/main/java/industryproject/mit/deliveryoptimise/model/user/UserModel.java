package industryproject.mit.deliveryoptimise.model.user;

import android.content.Context;

import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.network.RegisterRequest;
import industryproject.mit.deliveryoptimise.network.SigninRequest;
import industryproject.mit.deliveryoptimise.network.UserInfoEditRequest;
import industryproject.mit.deliveryoptimise.presenter.user.UserPresenterImpl;
import industryproject.mit.deliveryoptimise.view.user.IUserView;

/**
 * It is the Model in MVP pattern. For operating the data about the user information.
 */
public class UserModel implements IUser {
    private Context context;
    private IUserView iLoginView;
    private UserPresenterImpl presenter;

    public UserModel(Context context, IUserView iLoginView){
        this.context = context;
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(String username, String password) {
        presenter = new UserPresenterImpl(context, iLoginView);
        //Send the login request to the back-end server, and take the response back to the presenter.
        new SigninRequest(context, username, password).getData().subscribe(loginResponse -> presenter.loginResult(loginResponse), e -> presenter.loginResult(null));
    }

    @Override
    public void register(UserInfo userInfo) {
        presenter = new UserPresenterImpl(context, iLoginView);
        //Send the registration request to the back-end server, and take the response back to the presenter.
        new RegisterRequest(context, userInfo).getData().subscribe(registerResponse -> presenter.registerResult(registerResponse), e -> presenter.loginResult(null));
    }

    @Override
    public void editUserInfo(UserInfo userInfo) {
        presenter = new UserPresenterImpl(context, iLoginView);
        //Send the user information edit request to the back-end server, and take the response back to the presenter.
        new UserInfoEditRequest(context, userInfo).getData().subscribe(loginResponse -> presenter.editResult(loginResponse), e -> presenter.loginResult(null));
    }
}
