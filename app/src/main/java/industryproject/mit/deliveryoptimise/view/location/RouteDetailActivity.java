package industryproject.mit.deliveryoptimise.view.location;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.map.LegInfo;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryAddress;
import industryproject.mit.deliveryoptimise.presenter.address.DeliveryAddressPresenterImpl;

public class RouteDetailActivity extends BaseActivity implements IDeliveryAddressesView {
    private TextView tv_title, tv_origin, tv_destination, tv_distance, tv_duration,
            tv_departed, tv_arrived, tv_spent;
    private RelativeLayout lyt_back, lyt_departed, lyt_arrived, lyt_spent;
    private Button btn_time;
    private ScrollView sv_all;
    private DeliveryAddressPresenterImpl presenter;
    private LegInfo legInfo;
    private DeliveryAddress deliveryAddress;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_route_detail);
        tv_title = findViewById(R.id.tv_title);
        tv_origin = findViewById(R.id.tv_origin);
        tv_destination = findViewById(R.id.tv_destination);
        tv_distance = findViewById(R.id.tv_distance);
        tv_duration = findViewById(R.id.tv_duration);
        tv_departed = findViewById(R.id.tv_departed);
        tv_arrived = findViewById(R.id.tv_arrived);
        tv_spent = findViewById(R.id.tv_spent);
        lyt_back = findViewById(R.id.lyt_back);
        lyt_departed = findViewById(R.id.lyt_departed);
        lyt_arrived = findViewById(R.id.lyt_arrived);
        lyt_spent = findViewById(R.id.lyt_spent);
        btn_time = findViewById(R.id.btn_time);
        sv_all = findViewById(R.id.sv_all);

    }

    @Override
    protected void initData() {
        tv_title.setText("ROUTE DETAIL");
        lyt_back.setVisibility(View.VISIBLE);
        legInfo = (LegInfo) getIntent().getSerializableExtra(Constants.KEY_INTENT_LEG);
        viewOfAll(View.GONE);
        tv_origin.setText(legInfo.getStart_address());
        tv_destination.setText(legInfo.getEnd_address());
        tv_distance.setText(legInfo.getDistance().getText());
        tv_duration.setText(legInfo.getDuration().getText());
        presenter = new DeliveryAddressPresenterImpl(this, this);
        presenter.getDeliveryAddress(legInfo.getId());
        viewByStatus();
    }

    @Override
    protected void initListener() {
        lyt_back.setOnClickListener(this);
        btn_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lyt_back:
                this.finish();
                break;
            case R.id.btn_time:
                switch (deliveryAddress.getStatus()){
                    case Constants.STATUS_INIT:
                        presenter.requsetDeparture(deliveryAddress.getId());
                        break;
                    case Constants.STATUS_DEPARTED:
                        presenter.requestArrived(deliveryAddress.getId());
                        break;
                }
                break;
        }
    }

    private void viewByStatus() {
        if (deliveryAddress != null)
        switch (deliveryAddress.getStatus()){
            case Constants.STATUS_INIT:
                lyt_departed.setVisibility(View.GONE);
                lyt_arrived.setVisibility(View.GONE);
                lyt_spent.setVisibility(View.GONE);
                btn_time.setText("Departed");
                break;
            case Constants.STATUS_DEPARTED:
                lyt_departed.setVisibility(View.VISIBLE);
                lyt_arrived.setVisibility(View.GONE);
                lyt_spent.setVisibility(View.GONE);
//                tv_departed.setText();
                btn_time.setText("Arrived");
                break;
            case Constants.STATUS_ARRIVED:
                lyt_departed.setVisibility(View.VISIBLE);
                lyt_arrived.setVisibility(View.VISIBLE);
                lyt_spent.setVisibility(View.VISIBLE);
//                tv_departed.setText();
//                tv_arrived.setText();
//                tv_spent.setText(legStored.getSpent_time() + " mins");
                btn_time.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void getAddressesResult(List<DeliveryAddress> addresses, String message) {

    }

    @Override
    public void getAddressResult(DeliveryAddress address, String message) {
        if (address == null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }else{
            deliveryAddress = address;
            viewOfAll(View.VISIBLE);
            viewByStatus();
        }
    }

    @Override
    public void departureResult(DeliveryAddress address, String message) {

    }

    @Override
    public void arrivedResult(DeliveryAddress address, String message) {

    }

    /**
     * Whether show the page content
     * @param visible
     */
    private void viewOfAll(int visible){
        btn_time.setVisibility(visible);
        sv_all.setVisibility(visible);
    }
}
