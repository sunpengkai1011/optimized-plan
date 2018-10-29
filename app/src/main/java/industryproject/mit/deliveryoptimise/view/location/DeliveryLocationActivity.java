package industryproject.mit.deliveryoptimise.view.location;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.view.map.MapActivity;
import industryproject.mit.deliveryoptimise.adapter.LocationsAdapter;
import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.location.DeliveryLocationPresenterImpl;

public class DeliveryLocationActivity extends BaseActivity implements IDeliveryLocationView{
    private TextView tv_title;
    private RelativeLayout lyt_back;
    private RecyclerView rv_locations;
    private LocationsAdapter adapter;
    private Button btn_map;
    private DeliveryLocationPresenterImpl deliveryLocationPresenter;
    private DeliveryLocations locations;
    private UserInfo userInfo;

    private boolean isQuit = false;

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
        setContentView(R.layout.activity_delivery_location);
        tv_title = findViewById(R.id.tv_title);
        lyt_back = findViewById(R.id.lyt_back);
        rv_locations = findViewById(R.id.rv_locations);
        btn_map = findViewById(R.id.btn_map);
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.title_act_locations);
        lyt_back.setVisibility(View.VISIBLE);
        userInfo = (UserInfo) getIntent().getSerializableExtra(Constants.KEY_INTENT_USERINFO);
        deliveryLocationPresenter = new DeliveryLocationPresenterImpl(this, this);
        deliveryLocationPresenter.getDeliveryLocations(userInfo.getUserId());
    }

    @Override
    protected void initListener() {
        lyt_back.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_map:
                intentToMap();
                break;
            case R.id.lyt_back:
                this.finish();
                break;
        }
    }

    @Override
    public void getDeliveryLocations(DeliveryLocations locations) {
        this.locations = locations;
        adapter = new LocationsAdapter(this, locations);
        rv_locations.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        rv_locations.setHasFixedSize(true);
        rv_locations.setNestedScrollingEnabled(false);
        rv_locations.setFocusable(false);
        rv_locations.setAdapter(adapter);
    }

    @Override
    public void getDeliveryLocationsError() {
        Toast.makeText(this, "Locations request failed", Toast.LENGTH_SHORT).show();
    }

    private void intentToMap(){
        Intent intent = new Intent(DeliveryLocationActivity.this, MapActivity.class);
        intent.putExtra(Constants.KEY_INTENT_ADDRESSES, locations);
        startActivity(intent);
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
