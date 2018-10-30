package industryproject.mit.deliveryoptimise.view.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.login.LoginPresenterImpl;


public class RegisterActivity extends BaseActivity implements ILoginView {
    private TextView tv_title;
    private EditText et_username, et_password, et_confirm_pwd, et_city, et_suburb, et_street;
    private Button btn_register;
    private RelativeLayout lyt_back;

    private LoginPresenterImpl registerPresenter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);

        tv_title = findViewById(R.id.tv_title);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_confirm_pwd = findViewById(R.id.et_confirm_pwd);
        et_city = findViewById(R.id.et_city);
        et_suburb = findViewById(R.id.et_suburb);
        et_street = findViewById(R.id.et_street);

        btn_register = findViewById(R.id.btn_register);

        lyt_back = findViewById(R.id.lyt_back);
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.title_register);
        lyt_back.setVisibility(View.VISIBLE);
        registerPresenter = new LoginPresenterImpl(this, this);
    }

    @Override
    protected void initListener() {
        btn_register.setOnClickListener(this);
        lyt_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                registerUser();
                break;
            case R.id.lyt_back:
                this.finish();
                break;
        }
    }

    @Override
    public void loginResult(UserInfo userInfo, int code) {
    }

    @Override
    public void registerResult(UserInfo userInfo, int code) {
        switch (code){
            case Constants.RESPONSE_CODE_FAIL:
                Toast.makeText(this, "Register Failed!", Toast.LENGTH_SHORT).show();
                break;
            case Constants.RESPONSE_CODE_SUCCESSFUL:
                Toast.makeText(this, "Register Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.putExtra(Constants.KEY_INTENT_USERINFO, userInfo);
                RegisterActivity.this.setResult(RESULT_OK, intent);
                this.finish();
                break;
        }
    }

    private void registerUser(){
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String confirm_pwd = et_confirm_pwd.getText().toString().trim();
        String city = et_city.getText().toString().trim();
        String suburb = et_suburb.getText().toString().trim();
        String street = et_street.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirm_pwd)
                && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(suburb) && !TextUtils.isEmpty(street)){
            if (!password.equals(confirm_pwd)){
                Toast.makeText(this, getResources().getString(R.string.toast_pwd_different), Toast.LENGTH_SHORT).show();
            }else{
                UAddress address = new UAddress(city, suburb, street);
                UserInfo userInfo = new UserInfo(username, password, address);
                registerPresenter.doRegister(userInfo);
            }
        }else {
            if (TextUtils.isEmpty(username)){
                Toast.makeText(this, getResources().getString(R.string.toast_empty_username), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(password)){
                Toast.makeText(this, getResources().getString(R.string.toast_empty_password), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(confirm_pwd)){
                Toast.makeText(this, getResources().getString(R.string.toast_confirm_pwd), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(city)){
                Toast.makeText(this, getResources().getString(R.string.toast_city), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(suburb)){
                Toast.makeText(this, getResources().getString(R.string.toast_suburb), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(street)){
                Toast.makeText(this, getResources().getString(R.string.toast_street), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
