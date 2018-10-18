package industryproject.mit.deliveryoptimise.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.adapter.AddressesAdapter;
import industryproject.mit.deliveryoptimise.entities.RequestAddressInfo;
import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.network.RouteRequest;
import industryproject.mit.deliveryoptimise.utils.LocationUtil;

public class AddressListActivity extends BaseActivity implements RequestCallBack{
    private TextView tv_title;
    private RelativeLayout lyt_back;
    private ListView lv_address;
    private Button btn_map;
    private int address;
    private RequestAddressInfo info;
    private List<RequestAddressInfo> infos;
    private RouteResponse routeResponse;

    private boolean isNotRenew = false;

    private String myLocation;


    @Override
    void initView() {
        setContentView(R.layout.activity_addresslist);
        tv_title = findViewById(R.id.tv_title);
        lyt_back = findViewById(R.id.lyt_back);
        lv_address = findViewById(R.id.lv_address);
        btn_map = findViewById(R.id.btn_map);
    }

    @Override
    void initData() {
        tv_title.setText(R.string.title_addressAct);
        lyt_back.setVisibility(View.VISIBLE);

        address = getIntent().getIntExtra("addresses", 0);
        myLocation = LocationUtil.getMyLocationAddress(this);
        if (!TextUtils.isEmpty(myLocation)){
            Toast.makeText(this, myLocation, Toast.LENGTH_SHORT).show();
        }
        textData();
        info = infos.get(address);

        AddressesAdapter adapter = new AddressesAdapter(this, getItems(), 1);
        lv_address.setAdapter(adapter);
    }

    @Override
    void initListener() {
        lyt_back.setOnClickListener(this);
        btn_map.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_map:
                if (isNotRenew && routeResponse != null){
                    intentToMap();
                }
                else {
                    RouteRequest routeRequest = new RouteRequest(this, this, info.getOrigin(), info.getDestination(), info.getWaypointsStr());
                    routeRequest.getRequest();
                }
                break;
            case R.id.lyt_back:
                this.finish();
                break;
        }
    }

    @Override
    public void requestCallBack(Object o) {
        isNotRenew = true;
        routeResponse = (RouteResponse) o;
        intentToMap();
    }

    @Override
    public void requestFailure(Object o) {

    }

    private void textData() {
        infos = new ArrayList<>();
        RequestAddressInfo addressOne = new RequestAddressInfo();
        addressOne.setOrigin(myLocation);
        addressOne.setDestination("Manukau Station,Auckland");
        List waypoint = new ArrayList();
        waypoint.add("Airport,Auckland");
        waypoint.add("Sylvia Park Station,Auckland");
        waypoint.add("Botany Town Center,Auckland");
        waypoint.add("Mt Eden,Auckland");
        waypoint.add("Mission Bay,Auckland");
        waypoint.add("One Tree Hill,Auckland");
        addressOne.setWaypoints(waypoint);
        infos.add(addressOne);

        RequestAddressInfo addressTwo = new RequestAddressInfo();
        addressTwo.setOrigin("Airport,Auckland");
        addressTwo.setDestination("Sylvia Park Station,Auckland");
        List waypoint1 = new ArrayList();
        waypoint1.add("Mt Eden,Auckland");
        waypoint1.add("Manukau Station,Auckland");
        waypoint1.add("Botany Town Center,Auckland");
        addressTwo.setWaypoints(waypoint1);
        infos.add(addressTwo);

        RequestAddressInfo addressThree = new RequestAddressInfo();
        addressThree.setOrigin("One Tree Hill,Auckland");
        addressThree.setDestination("One Tree Hill,Auckland");
        List waypoint2 = new ArrayList();
        waypoint2.add("Mission Bay,Auckland");
        waypoint2.add("Devon Port,Auckland");
        waypoint2.add("Airport,Auckland");
        addressThree.setWaypoints(waypoint2);
        infos.add(addressThree);

        RequestAddressInfo addressFour = new RequestAddressInfo();
        addressFour.setOrigin("Mission Bay,Auckland");
        addressFour.setDestination("Mission Bay,Auckland");
        List waypoint3 = new ArrayList();
        waypoint3.add("One Tree Hill,Auckland");
        waypoint3.add("Botany Town Center,Auckland");
        waypoint3.add("Devon Port,Auckland");
        addressFour.setWaypoints(waypoint3);
        infos.add(addressFour);
    }

    private List<String> getItems(){
        List<String> items = new ArrayList<>();
        items.add(info.getOrigin());
        for (String item : info.getWaypoints()){
            items.add(item);
        }
        items.add(info.getDestination());
        return items;
    }

    private void intentToMap(){
        Intent intent = new Intent(AddressListActivity.this, MapActivity.class);
        intent.putExtra("response", routeResponse);
        startActivity(intent);
    }
}
