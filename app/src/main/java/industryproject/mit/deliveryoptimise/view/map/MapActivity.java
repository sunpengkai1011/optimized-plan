package industryproject.mit.deliveryoptimise.view.map;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.adapter.LegRouteAdapter;
import industryproject.mit.deliveryoptimise.entities.LegInfo;
import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import industryproject.mit.deliveryoptimise.presenter.map.MapPresenterImpl;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;
import industryproject.mit.deliveryoptimise.utils.MapUtil;
import industryproject.mit.deliveryoptimise.utils.PermissionUtil;

public class MapActivity extends BaseActivity implements
        IMapView, GoogleMap.OnMapLoadedCallback, RecyclerViewPager.OnPageChangedListener,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener, OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener{
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private boolean mPermissionDenied = false;

    private TextView tv_title, tv_num_markers, tv_distance, tv_duration;
    private CardView cv_route_info;
    private RelativeLayout lyt_back, lyt_navigate;
    private RecyclerViewPager rvp_routes;
    private List<UAddress> addresses;
    private RouteResponse response;

    private int currentLeg = 0;

    private MapPresenterImpl mapPresenter;
    private boolean isLoaded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        addresses = (ArrayList<UAddress>) getIntent().getExtras().getSerializable(Constants.KEY_INTENT_ADDRESSES);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(){
        setContentView(R.layout.activity_map);
        tv_title = findViewById(R.id.tv_title);
        lyt_back = findViewById(R.id.lyt_back);
        lyt_navigate = findViewById(R.id.lyt_navigate);
        cv_route_info = findViewById(R.id.cv_route_info);
        tv_num_markers = findViewById(R.id.tv_num_markers);
        tv_distance = findViewById(R.id.tv_distance);
        tv_duration = findViewById(R.id.tv_duration);
    }

    @Override
    protected void initData(){
        lyt_navigate.setVisibility(View.VISIBLE);
        tv_title.setText(R.string.title_act_map);
        lyt_back.setVisibility(View.VISIBLE);



        mapPresenter = new MapPresenterImpl(this, this);
        mapPresenter.requestOptimiseRoutes(addresses);

        rvp_routes = findViewById(R.id.rvp_routes);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frg_map);
        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    protected void initListener(){
        lyt_back.setOnClickListener(this);
        lyt_navigate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lyt_back:
                MapActivity.this.finish();
                break;
            case R.id.lyt_navigate:
                MapUtil.intentToGoogleMap(MapActivity.this, response.getRoutes().get(0));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) { }

    @Override
    public void onConnectionSuspended(int i) { }

    @Override
    public boolean onMarkerClick(Marker marker) {
        final Marker currentMarker = marker;
        GeneralUtil.mapMarkerDialog(this, marker.getSnippet(), getResources().getString(R.string.btnMessage_depart), new Dialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MapActivity.this, "depart", Toast.LENGTH_SHORT).show();
                if (Constants.realTime_depart.containsKey(currentMarker.getId()) &&
                        Constants.realTime_depart.get(currentMarker.getId()) != 0){
                    GeneralUtil.twoBtnDialog(MapActivity.this, getResources().getString(R.string.btnMessage_depart_clicked),
                            getResources().getString(R.string.btnMessage_sure),
                            new Dialog.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Constants.realTime_depart.put(currentMarker.getId(), System.currentTimeMillis());
                                }
                            },
                            getResources().getString(R.string.btnMessage_cancel),
                            new Dialog.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                }else {
                    Constants.realTime_depart.put(currentMarker.getId(), System.currentTimeMillis());
                }
            }
        }, getResources().getString(R.string.btnMessage_arrive), new Dialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MapActivity.this, "arrive", Toast.LENGTH_SHORT).show();
            }
        }, getResources().getString(R.string.btnMessage_cancel), new Dialog.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "connection failed", Toast.LENGTH_SHORT).show();
    }

    /**
            * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtil.requestPermission(MapActivity.this, Constants.LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != Constants.LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtil.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtil.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnPageChanged(int i, int i1) {
        currentLeg = i1;
        drawLegRoute(i1);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (cv_route_info.getVisibility() == View.VISIBLE){
            cv_route_info.setVisibility(View.GONE);
        }else {
            cv_route_info.setVisibility(View.VISIBLE);
        }
        rvp_routes.setVisibility(View.GONE);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (rvp_routes.getVisibility() == View.VISIBLE){
            drawMarkandRoute();
        }else {
            drawLegRoute(currentLeg);
        }
        cv_route_info.setVisibility(View.GONE);
    }

    private void drawMarkandRoute(){
        mMap.clear();
        MapUtil.addMarkers(mMap, response.getRoutes().get(0).getLegs());
        MapUtil.drawRoute(mMap, this, response.getRoutes().get(0).getLegs());
        rvp_routes.setVisibility(View.GONE);
    }

    private void drawLegRoute(int position){
        mMap.clear();
        MapUtil.addMarkers(mMap, response.getRoutes().get(0).getLegs());
        MapUtil.drawRoute(mMap, this, response.getRoutes().get(0).getLegs(), position);
        rvp_routes.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMapLoaded() {
        isLoaded = true;
        if(response != null) {
            MapUtil.displayArea(mMap, this, response.getRoutes().get(0).getBounds(), 40);
        }
    }

    private int[] getTotalDistanceAndDuration(){
        int totalDuration = 0;
        int totalDistance = 0;
        for(LegInfo info : response.getRoutes().get(0).getLegs()){
            totalDuration += info.getDuration().getValue();
            totalDistance += info.getDistance().getValue();
        }
        return new int[]{totalDistance, totalDuration};
    }

    @Override
    public void routeResponse(RouteResponse response) {
        this.response = response;
        if(isLoaded) {
            MapUtil.displayArea(mMap, this, response.getRoutes().get(0).getBounds(), 40);
        }
        rvp_routes.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        LegRouteAdapter adapter = new LegRouteAdapter(this, response.getRoutes().get(0).getLegs());
        rvp_routes.addOnPageChangedListener(this);
        rvp_routes.setAdapter(adapter);

        tv_num_markers.setText("The total delivery locations are : " + response.getRoutes().get(0).getLegs().size());
        tv_duration.setText("The total duration is : " + GeneralUtil.secondToMinutes(getTotalDistanceAndDuration()[1]));
        tv_distance.setText("The total distance is : " + GeneralUtil.mToKm(getTotalDistanceAndDuration()[0]));

        MapUtil.addMarkers(mMap, response.getRoutes().get(0).getLegs());
        MapUtil.drawRoute(mMap, this, response.getRoutes().get(0).getLegs());
    }

    @Override
    public void routeResponseError() {
        Toast.makeText(this, "Routes request failed", Toast.LENGTH_SHORT).show();
    }
}
