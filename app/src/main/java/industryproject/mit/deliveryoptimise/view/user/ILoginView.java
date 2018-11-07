package industryproject.mit.deliveryoptimise.view.user;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

public interface ILoginView {
    /**
     * The callback of login request
     * @param userInfo user information
     * @param message the message of response
     *
     */
    void loginResult(UserInfo userInfo, String message);

    /**
     * The callback of registration request.
     * @param isSuccess
     * @param message prompt message
     */
    void registerResult(boolean isSuccess, String message);

    /**
     * The callback of user information edit request
     * @param userInfo user information
     * @param message the message of response
     */
    void editUserInfoResult(UserInfo userInfo, String message);
}
