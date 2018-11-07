package industryproject.mit.deliveryoptimise.view.user;

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
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.user.LoginPresenterImpl;


/**
 * Registration page
 */
public class RegisterActivity extends BaseActivity implements ILoginView {
    private TextView tv_title;
    private EditText et_username, et_password, et_confirm_pwd, et_phone, et_email;
    private Button btn_register;
    private String username;
    private RelativeLayout lyt_back;

    private LoginPresenterImpl registerPresenter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);

        tv_title = findViewById(R.id.tv_title);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_confirm_pwd = findViewById(R.id.et_confirm_pwd);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);

        btn_register = findViewById(R.id.btn_register);

        lyt_back = findViewById(R.id.lyt_back);
    }

    @Override
    protected void initData() {
        //Set the title text.
        tv_title.setText(R.string.title_register);
        //Display the back button.
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
    public void loginResult(UserInfo userInfo, String message) {

    }

    @Override
    public void registerResult(boolean isSuccess, String message) {
        if (isSuccess){
            //If the registration request is successful, jump to the Login page and send the username to Login page.
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra(Constants.KEY_INTENT_USERNAME, username);
            RegisterActivity.this.setResult(RESULT_OK, intent);
            this.finish();
        }else{
            //If the registration request is failed, give the prompt.
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void editUserInfoResult(UserInfo userInfo, String message) {

    }

    /**
     * Register new user account
     */
    private void registerUser(){
        //Get the users input information.
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String confirm_pwd = et_confirm_pwd.getText().toString().trim();
        String phone = et_phone.getText().toString().trim();
        String email = et_email.getText().toString().trim();

        //Determine whether the user input information is empty.
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirm_pwd)
                && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(email)){
            //Determine the twice input passwords whether is same.
            if (!password.equals(confirm_pwd)){
                //Give the prompt
                Toast.makeText(this, getResources().getString(R.string.toast_pwd_different), Toast.LENGTH_SHORT).show();
            }else{
                //Start to register the new user account
                UserInfo userInfo = new UserInfo(username, password, phone, email);
                this.username = username;
                registerPresenter.doRegister(userInfo);
            }
        }else {
            //If an item is empty give a corresponding prompt
            if (TextUtils.isEmpty(username)){
                Toast.makeText(this, getResources().getString(R.string.toast_empty_username), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(password)){
                Toast.makeText(this, getResources().getString(R.string.toast_empty_password), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(confirm_pwd)){
                Toast.makeText(this, getResources().getString(R.string.toast_confirm_pwd), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(phone)){
                Toast.makeText(this, getResources().getString(R.string.toast_phone), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(email)){
                Toast.makeText(this, getResources().getString(R.string.toast_email), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
