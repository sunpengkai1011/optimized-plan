package industryproject.mit.deliveryoptimise.view.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.user.LoginPresenterImpl;
import industryproject.mit.deliveryoptimise.view.location.DeliveryAddressesActivity;

/**
 * Login page
 */
public class LoginActivity extends BaseActivity implements ILoginView{
    private TextView tv_title, tv_to_register;
    private EditText et_username, et_password;
    private Button btn_login;

    private LoginPresenterImpl loginPresenterImpl;

    private boolean isQuit = false;
    private String username;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isQuit = false;
        }
    };

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
        //Set the title text.
        tv_title.setText(getResources().getString(R.string.title_login));
        //Add the under line.
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
                //Get the input username and password
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //Determine the username and password whether are empty.
                if (!TextUtils.isEmpty(username) &&
                        !TextUtils.isEmpty(password)){
                    //If they are not empty, start to login.
                    loginPresenterImpl.doLogin(username, password);
                }else{
                    //If any item is empty, give the corresponding prompt.
                    if (TextUtils.isEmpty(username)){
                        Toast.makeText(this, getResources().getString(R.string.toast_empty_username), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, getResources().getString(R.string.toast_empty_password), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.tv_to_register:
                //Clicked the register button to jump to the Registration Page.
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, Constants.INTENT_REQUEST_LOGIN_TO_REGISTER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            //Get the username from the Registration page, and show it.
            username = data.getStringExtra(Constants.KEY_INTENT_USERNAME);
            if (!TextUtils.isEmpty(username)) {
                et_username.setText(username);
            }
        }
    }

    @Override
    public void loginResult(UserInfo userInfo, String message) {
        if (userInfo == null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            //If the login request is successful, jump to the next page.
            Constants.userInfo = userInfo;
            Intent intent = new Intent(LoginActivity.this, DeliveryAddressesActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    @Override
    public void registerResult(boolean isSuccess, String message) {

    }

    @Override
    public void editUserInfoResult(UserInfo userInfo, String message) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //If click the Back key twice in 2 seconds, the program exits
            if (!isQuit) {
                isQuit = true;
                Toast.makeText(getApplicationContext(), "Press the return key again to exit the program!",
                        Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }
}
