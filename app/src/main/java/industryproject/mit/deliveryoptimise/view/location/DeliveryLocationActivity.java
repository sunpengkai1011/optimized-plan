package industryproject.mit.deliveryoptimise.view.location;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import industryproject.mit.deliveryoptimise.view.map.MapActivity;
import industryproject.mit.deliveryoptimise.adapter.LocationsAdapter;
import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import industryproject.mit.deliveryoptimise.entities.UserInfo;
import industryproject.mit.deliveryoptimise.presenter.location.DeliveryLocationPresenterImpl;

public class DeliveryLocationActivity extends BaseActivity implements IDeliveryLocationView{
    private TextView tv_title;
    private RelativeLayout lyt_back;
    private RecyclerView rv_locations;
    private LocationsAdapter adapter;
    private Button btn_map;
    private DeliveryLocationPresenterImpl deliveryLocationPresenter;
    private List<UAddress> addresses;
    private UserInfo userInfo;

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
    public void getDeliveryLocations(List<UAddress> addresses) {
        this.addresses = addresses;
        adapter = new LocationsAdapter(this, addresses);
        rv_locations.setLayoutManager(new LinearLayoutManager(this));
        rv_locations.setAdapter(adapter);
    }

    @Override
    public void getDeliveryLocationsError() {
        Toast.makeText(this, "Locations request failed", Toast.LENGTH_SHORT).show();
    }

    private void intentToMap(){
        ArrayList<UAddress> list = (ArrayList<UAddress>) addresses;
        Intent intent = new Intent(DeliveryLocationActivity.this, MapActivity.class);
        intent.putExtra(Constants.KEY_INTENT_ADDRESSES, list);
        startActivity(intent);
    }
}
