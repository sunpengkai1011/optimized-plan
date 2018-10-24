package industryproject.mit.deliveryoptimise.presenter.register;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.model.user.IUserRegister;
import industryproject.mit.deliveryoptimise.model.user.UserRegisterModel;
import industryproject.mit.deliveryoptimise.view.register.IRegisterView;

public class RegisterPresenterImpl implements IRegisterPresenter {
    private IRegisterView iRegisterView;
    private IUserRegister iUserRegister;
    private Context context;

    public RegisterPresenterImpl(Context context, IRegisterView iRegisterView){
        this.context = context;
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void doRegister(UserInfo userInfo) {
        if (userInfo != null) {
            iUserRegister = new UserRegisterModel(context);
            if (iUserRegister.register(userInfo)) {
                iRegisterView.registerResult(iUserRegister.getUserInfo(userInfo.getUserName()), Constants.RESPONSE_CODE_SUCCESSFUL);
            } else {
                iRegisterView.registerResult(userInfo, Constants.RESPONSE_CODE_FAIL);
            }
        }else{
            iRegisterView.registerResult(new UserInfo(), Constants.RESPONSE_CODE_FAIL);
        }
    }
}
