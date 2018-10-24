package industryproject.mit.deliveryoptimise.model.user;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

public interface IUserRegister {

    UserInfo getUserInfo(String username);
    boolean register(UserInfo userInfo);
}
