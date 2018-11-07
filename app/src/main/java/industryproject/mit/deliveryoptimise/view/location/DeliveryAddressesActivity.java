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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryAddress;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.view.map.MapActivity;
import industryproject.mit.deliveryoptimise.adapter.LocationsAdapter;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.address.DeliveryAddressPresenterImpl;
import industryproject.mit.deliveryoptimise.view.user.LoginActivity;
import industryproject.mit.deliveryoptimise.view.user.UserEditActivity;

public class DeliveryAddressesActivity extends BaseActivity implements IDeliveryAddressesView {
    private TextView tv_title, tv_origin, tv_destination;
    private RelativeLayout lyt_right;
    private ImageView iv_icon;
    private RecyclerView rv_locations;
    private LocationsAdapter adapter;
    private DeliveryLocations deliveryLocations;
    private Button btn_map;
    private DeliveryAddressPresenterImpl deliveryLocationPresenter;
    private List<DeliveryAddress> addresses;
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int statusType = intent.getIntExtra(Constants.KEY_INTENT_CLOSETYPE, 0);
        if (Constants.CLOSETYPE_LOGOUT == statusType){
            //open login page
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
            finish();
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_delivery_location);
        tv_title = findViewById(R.id.tv_title);
        tv_origin = findViewById(R.id.tv_origin);
        tv_destination = findViewById(R.id.tv_destination);
        rv_locations = findViewById(R.id.rv_locations);
        btn_map = findViewById(R.id.btn_map);
        lyt_right = findViewById(R.id.lyt_right);
        iv_icon = findViewById(R.id.iv_icon);
    }

    @Override
    protected void initData() {
        tv_title.setText(R.string.title_act_locations);
        lyt_right.setVisibility(View.VISIBLE);
        iv_icon.setImageResource(R.drawable.icon_user);
        deliveryLocationPresenter = new DeliveryAddressPresenterImpl(this, this);
        deliveryLocationPresenter.getDeliveryAddresses();
    }

    @Override
    protected void initListener() {
        lyt_right.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_map:
                intentToMap();
                break;
            case R.id.lyt_right:
                Intent intent = new Intent(DeliveryAddressesActivity.this, UserEditActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getAddressesResult(List<DeliveryAddress> addresses, String message) {
        if (addresses == null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else {
            sortingData(addresses);
            tv_origin.setText(deliveryLocations.getOrigin().toString());
            tv_destination.setText(deliveryLocations.getDestination().toString());
            adapter = new LocationsAdapter(this, deliveryLocations);
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
    }

    private void intentToMap(){
        Intent intent = new Intent(DeliveryAddressesActivity.this, MapActivity.class);
        intent.putExtra(Constants.KEY_INTENT_ADDRESSES, deliveryLocations);
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

    private void sortingData(List<DeliveryAddress> addresses){
        deliveryLocations = new DeliveryLocations();
        deliveryLocations.getWayPoints().clear();
        for(DeliveryAddress address : addresses){
            switch (address.getType_id()){
                case Constants.ADDRESS_TYPE_ORIGIN:
                    deliveryLocations.setOrigin(address);
                    break;
                case Constants.ADDRESS_TYPE_DESTINATION:
                    deliveryLocations.setDestination(address);
                    break;
                case Constants.ADDRESS_TYPE_WAYPOINT:
                    deliveryLocations.getWayPoints().add(address);
                    break;
            }
        }
    }
}
