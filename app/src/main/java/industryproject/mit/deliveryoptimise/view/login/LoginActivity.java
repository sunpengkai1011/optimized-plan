package industryproject.mit.deliveryoptimise.view.login;

import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.login.LoginPresenterImpl;
import industryproject.mit.deliveryoptimise.view.location.DeliveryLocationActivity;
import industryproject.mit.deliveryoptimise.view.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements ILoginView{
    private TextView tv_title, tv_to_register;
    private EditText et_username, et_password;
    private Button btn_login;

    private LoginPresenterImpl loginPresenterImpl;

    private UserInfo userInfo;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        tv_title = findViewById(R.id.tv_title);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_to_register = findViewById(R.id.tv_to_register);
    }

    @Override
    protected void initData() {
        tv_title.setText(getResources().getString(R.string.title_login));
        tv_to_register.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        loginPresenterImpl = new LoginPresenterImpl(this, this);
    }

    @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
        tv_to_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (!TextUtils.isEmpty(username) &&
                        !TextUtils.isEmpty(password)){
                    loginPresenterImpl.doLogin(username, password);
                }else{
                    if (TextUtils.isEmpty(username)){
                        Toast.makeText(this, getResources().getString(R.string.toast_empty_username), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, getResources().getString(R.string.toast_empty_password), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.tv_to_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, Constants.INTENT_REQUEST_LOGIN_TO_REGISTER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            userInfo = (UserInfo) data.getSerializableExtra(Constants.KEY_INTENT_USERINFO);
            if (!TextUtils.isEmpty(userInfo.getUserName())) {
                et_username.setText(userInfo.getUserName());
            }
        }
    }

    @Override
    public void loginResult(UserInfo userInfo, int code) {
        switch (code){
            case Constants.RESPONSE_CODE_FAIL:
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show();
                break;
            case Constants.RESPONSE_CODE_SUCCESSFUL:
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DeliveryLocationActivity.class);
                intent.putExtra(Constants.KEY_INTENT_USERINFO, userInfo);
                startActivity(intent);
                this.finish();
                break;
        }
    }
}
