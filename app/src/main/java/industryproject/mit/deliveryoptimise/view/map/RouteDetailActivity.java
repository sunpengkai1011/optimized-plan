package industryproject.mit.deliveryoptimise.view.map;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.map.LegStored;
import industryproject.mit.deliveryoptimise.entities.map.RouteStored;
import industryproject.mit.deliveryoptimise.presenter.map.MapPresenterImpl;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;

public class RouteDetailActivity extends BaseActivity implements IRouteStroedView {
    private TextView tv_title, tv_origin, tv_destination, tv_distance, tv_duration,
            tv_departed, tv_arrived, tv_spent;
    private RelativeLayout lyt_back, lyt_departed, lyt_arrived, lyt_spent;
    private Button btn_time;
    private MapPresenterImpl mapPresenter;
    private RouteStored routeStored;
    private LegStored legStored;
    private int position;

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

    }

    @Override
    protected void initData() {
        tv_title.setText("ROUTE DETAIL");
        lyt_back.setVisibility(View.VISIBLE);
        routeStored = (RouteStored) getIntent().getSerializableExtra(Constants.KEY_INTENT_ROUTESTORED);
        position = getIntent().getIntExtra(Constants.KEY_INTENT_POSITION, 0);
        legStored = routeStored.getLegStoreds().get(position);

        tv_origin.setText(legStored.getStart_address());
        tv_destination.setText(legStored.getEnd_address());
        tv_distance.setText(legStored.getDistance().getText());
        tv_duration.setText(legStored.getDuration().getText());

        viewByStatus();
        mapPresenter = new MapPresenterImpl(this,this);
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
                switch (legStored.getStatus()){
                    case Constants.STATUS_INIT:
                        mapPresenter.departedTimeStored(System.currentTimeMillis(), position);
                        break;
                    case Constants.STATUS_DEPARTED:
                        mapPresenter.arrivedTimeStored(System.currentTimeMillis(), position);
                        break;
                }
                break;
        }
    }

    @Override
    public void departedTimeStored() {
        Toast.makeText(this, "Departed", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void departedTimeStoredError() {
        Toast.makeText(this, "Request Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void arrivedTimeStored() {
        Toast.makeText(this, "Arrived", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        this.finish();
    }

    @Override
    public void arrivedTimeStoredError() {
        Toast.makeText(this, "Request Failed", Toast.LENGTH_SHORT).show();
    }

    private void viewByStatus() {
        if (legStored != null)
        switch (legStored.getStatus()){
            case Constants.STATUS_INIT:
                lyt_departed.setVisibility(View.GONE);
                lyt_arrived.setVisibility(View.GONE);
                lyt_spent.setVisibility(View.GONE);
                tv_departed.setText(GeneralUtil.getCurrentTime(legStored.getDepart_time()));
                btn_time.setText("Departed");
                break;
            case Constants.STATUS_DEPARTED:
                lyt_departed.setVisibility(View.VISIBLE);
                lyt_arrived.setVisibility(View.GONE);
                lyt_spent.setVisibility(View.GONE);
                tv_departed.setText(GeneralUtil.getCurrentTime(legStored.getDepart_time()));
                tv_arrived.setText(GeneralUtil.getCurrentTime(legStored.getArrive_time()));
                btn_time.setText("Arrived");
                break;
            case Constants.STATUS_ARRIVED:
                lyt_departed.setVisibility(View.VISIBLE);
                lyt_arrived.setVisibility(View.VISIBLE);
                lyt_spent.setVisibility(View.VISIBLE);
                tv_departed.setText(GeneralUtil.getCurrentTime(legStored.getDepart_time()));
                tv_arrived.setText(GeneralUtil.getCurrentTime(legStored.getArrive_time()));
                tv_spent.setText(legStored.getSpent_time() + " mins");
                btn_time.setVisibility(View.GONE);
                break;
        }
    }
}
