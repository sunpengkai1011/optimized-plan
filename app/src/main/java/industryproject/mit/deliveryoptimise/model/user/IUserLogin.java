package industryproject.mit.deliveryoptimise.model.user;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

/**
 * The interface of login. It is for MVP pattern.
 */
public interface IUserLogin {

    /**
     * Get the user information through username
     * @param username
     * @return user information
     */
    UserInfo getUserInfo(String username);

    /**
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);

    /**
     * @param userInfo
     * @return
     */
    boolean register(UserInfo userInfo);
}
