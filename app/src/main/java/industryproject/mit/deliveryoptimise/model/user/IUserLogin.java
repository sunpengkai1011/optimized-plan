package industryproject.mit.deliveryoptimise.model.user;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

/**
 * The interface of login. It is for MVP pattern.
 */
public interface IUserLogin {

    /**
     * @param username
     * @param password
     * @return
     */
    void login(String username, String password);

    /**
     * @param userInfo
     * @return
     */
    void register(UserInfo userInfo);

    /**
     * @param userInfo
     */
    void editUserInfo(UserInfo userInfo);
}
