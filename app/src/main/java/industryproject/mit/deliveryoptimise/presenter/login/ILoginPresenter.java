package industryproject.mit.deliveryoptimise.presenter.login;

import industryproject.mit.deliveryoptimise.entities.UserInfo;

public interface ILoginPresenter {
    void doLogin(String username, String password);
    void doRegister(UserInfo userInfo);
}
