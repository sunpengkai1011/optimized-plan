package industryproject.mit.deliveryoptimise.view.login;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

public interface ILoginView {
    void loginResult(UserInfo userInfo, int code);
}
