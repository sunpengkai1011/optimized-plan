package industryproject.mit.deliveryoptimise.view.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.presenter.user.LoginPresenterImpl;
import industryproject.mit.deliveryoptimise.view.location.DeliveryAddressesActivity;

public class UserEditActivity extends BaseActivity implements ILoginView {
    private TextView tv_title, tv_username, tv_user_role, tv_phone_number, tv_email;
    private EditText et_phone_number, et_email;
    private Button btn_commit;
    private ImageView iv_icon;
    private RelativeLayout lyt_back, lyt_right, lyt_user, lyt_edit;
    private UserInfo userInfo;

    private boolean isEdit = true;

    private LoginPresenterImpl meEditPresenter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_user_info);
        tv_title = findViewById(R.id.tv_title);
        tv_username = findViewById(R.id.tv_username);
        tv_user_role = findViewById(R.id.tv_user_role);
        tv_phone_number = findViewById(R.id.tv_phone_number);
        tv_email = findViewById(R.id.tv_email);

        et_phone_number = findViewById(R.id.et_phone_number);
        et_email = findViewById(R.id.et_email);


        btn_commit = findViewById(R.id.btn_commit);
        lyt_right = findViewById(R.id.lyt_right);
        lyt_back = findViewById(R.id.lyt_back);
        iv_icon = findViewById(R.id.iv_icon);

        lyt_user = findViewById(R.id.lyt_user);
        lyt_edit = findViewById(R.id.lyt_edit);
    }

    @Override
    public void initData() {
        tv_title.setText(getResources().getString(R.string.title_info));
        lyt_back.setVisibility(View.VISIBLE);
        setTextToEditView();
        meEditPresenter = new LoginPresenterImpl(this, this);
    }

    @Override
    public void initListener() {
        btn_commit.setOnClickListener(this);
        lyt_back.setOnClickListener(this);
        lyt_right.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_commit:
                if (isEdit) {
                    //Logout
                    Intent intent = new Intent(UserEditActivity.this, DeliveryAddressesActivity.class);
                    intent.putExtra(Constants.KEY_INTENT_CLOSETYPE, Constants.CLOSETYPE_LOGOUT);
                    startActivity(intent);
                }else {
                    editUserInfo();
                }
                break;
            case R.id.lyt_right:
                if (isEdit){
                    lyt_user.setVisibility(View.GONE);
                    lyt_edit.setVisibility(View.VISIBLE);
                    btn_commit.setText(getResources().getString(R.string.btn_edit));
                    tv_title.setText(getResources().getString(R.string.title_edit));
                    btn_commit.setBackgroundColor(getResources().getColor(R.color.background_title_bar));
                    isEdit = false;
                }else{
                    lyt_user.setVisibility(View.VISIBLE);
                    lyt_edit.setVisibility(View.GONE);
                    btn_commit.setText(getResources().getString(R.string.btnMessage_logout));
                    tv_title.setText(getResources().getString(R.string.title_info));
                    btn_commit.setBackgroundColor(getResources().getColor(R.color.background_logout));
                    isEdit = true;
                }
                break;
            case R.id.lyt_back:
                this.finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode){
            lyt_back.performClick();
        }
        return false;
    }

    @Override
    public void loginResult(UserInfo userInfo, String message) {

    }

    @Override
    public void registerResult(boolean isSuccess, String message) {

    }

    @Override
    public void editUserInfoResult(UserInfo userInfo, String message) {
        if(userInfo == null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Constants.userInfo = userInfo;
            setTextToEditView();
        }
    }


    private void setTextToEditView(){
        lyt_right.setVisibility(View.VISIBLE);
        iv_icon.setImageResource(R.drawable.icon_edit);
        lyt_user.setVisibility(View.VISIBLE);
        lyt_edit.setVisibility(View.GONE);
        btn_commit.setText(getResources().getString(R.string.btnMessage_logout));
        tv_title.setText(getResources().getString(R.string.title_info));
        btn_commit.setBackgroundColor(getResources().getColor(R.color.background_logout));
        userInfo = Constants.userInfo;
        tv_username.setText(userInfo.getUser_name());
        tv_phone_number.setText(userInfo.getPhone());
        tv_email.setText(userInfo.getEmail());
        et_phone_number.setText(userInfo.getPhone());
        et_email.setText(userInfo.getEmail());
    }

    private void editUserInfo(){
        String phoneNumber = et_phone_number.getText().toString().trim();
        String email = et_email.getText().toString().trim();

        if (!TextUtils.isEmpty(phoneNumber) && !TextUtils.isEmpty(email)){
            userInfo.setPhone(phoneNumber);
            userInfo.setEmail(email);
            meEditPresenter.editUserInfo(userInfo);
        }else {
            if (TextUtils.isEmpty(phoneNumber)){
                Toast.makeText(this, getResources().getString(R.string.toast_phone), Toast.LENGTH_SHORT).show();
            }else if (TextUtils.isEmpty(email)){
                Toast.makeText(this, getResources().getString(R.string.toast_email), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
