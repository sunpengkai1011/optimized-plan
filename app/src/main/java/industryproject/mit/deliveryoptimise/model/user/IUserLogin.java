package industryproject.mit.deliveryoptimise.model.user;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

public interface IUserLogin {

    UserInfo getUserInfo(String username);

    boolean login(String username, String password);
}
