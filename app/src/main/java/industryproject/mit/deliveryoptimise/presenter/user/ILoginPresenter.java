package industryproject.mit.deliveryoptimise.presenter.user;

import industryproject.mit.deliveryoptimise.entities.network.LoginResponse;
import industryproject.mit.deliveryoptimise.entities.network.RegisterResponse;
import industryproject.mit.deliveryoptimise.entities.UserInfo;

/**
 * The presenter which related with sign-in
 */
public interface ILoginPresenter {
    /**
     * Start to login
     * @param username input username
     * @param password input password
     */
    void doLogin(String username, String password);

    /**
     * Start to register the new user account.
     * @param userInfo User input information.
     */
    void doRegister(UserInfo userInfo);

    /**
     *
     * The callback of registration request.
     * @param response The response of login request
     */
    void loginResult(LoginResponse response);

    /**
     * The callback of registration request.
     * @param response The response of registration request
     */
    void registerResult(RegisterResponse response);

    /**
     * Start to edit the user information.
     * @param userInfo User input information.
     */
    void editUserInfo(UserInfo userInfo);

    /**
     * The callback of user information edit request
     * @param response The response of user information edit request
     */
    void editResult(LoginResponse response);
}
