package industryproject.mit.deliveryoptimise.view.map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import industryproject.mit.deliveryoptimise.BaseActivity;
import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.adapter.LegRouteAdapter;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.presenter.map.MapPresenterImpl;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;
import industryproject.mit.deliveryoptimise.utils.MapUtil;
import industryproject.mit.deliveryoptimise.utils.PermissionUtil;
import industryproject.mit.deliveryoptimise.view.location.RouteDetailActivity;

/**
 * Map page
 */
public class MapActivity extends BaseActivity implements
        IMapView, GoogleMap.OnMapLoadedCallback, RecyclerViewPager.OnPageChangedListener,
        GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener, LegRouteAdapter.OnItemClickListener{
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;

    private boolean mPermissionDenied = false;

    private TextView tv_title, tv_num_markers, tv_distance, tv_duration;
    private RelativeLayout lyt_back, lyt_right, lyt_route_info;
    private ImageView iv_icon;
    private RecyclerViewPager rvp_routes;
    private DeliveryLocations locations;
    private RouteResponse response;
    private Animation animation_in, animation_out;

    private int currentLeg = 0;

    private MapPresenterImpl mapPresenter;
    private boolean isLoaded = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Get the addresses information from last page.
        locations = (DeliveryLocations) getIntent().getExtras().getSerializable(Constants.KEY_INTENT_ADDRESSES);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(){
        setContentView(R.layout.activity_map);
        tv_title = findViewById(R.id.tv_title);
        lyt_back = findViewById(R.id.lyt_back);
        lyt_right = findViewById(R.id.lyt_right);
        iv_icon = findViewById(R.id.iv_icon);
        lyt_route_info = findViewById(R.id.lyt_route_info);
        tv_num_markers = findViewById(R.id.tv_num_markers);
        tv_distance = findViewById(R.id.tv_distance);
        tv_duration = findViewById(R.id.tv_duration);
    }

    @Override
    protected void initData(){
        //Display the right button of title bar.
        lyt_right.setVisibility(View.VISIBLE);
        //Set the right button image.
        iv_icon.setImageResource(R.drawable.logo_navigation);
        //Set the title text.
        tv_title.setText(R.string.title_act_map);
        //Display the back button.
        lyt_back.setVisibility(View.VISIBLE);
        //Define the animation.
        animation_in = AnimationUtils.loadAnimation(this, R.anim.anim_view_in);
        animation_out = AnimationUtils.loadAnimation(this, R.anim.anim_view_out);

        mapPresenter = new MapPresenterImpl(this, this);
        //Start to request the optimised routes.
        mapPresenter.requestOptimiseRoutes(locations);

        rvp_routes = findViewById(R.id.rvp_routes);

        //Google map setting
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frg_map);
        mapFragment.getMapAsync(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    protected void initListener(){
        lyt_back.setOnClickListener(this);
        lyt_right.setOnClickListener(this);
        //Set the animation listener.
        animation_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lyt_route_info.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animation_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                lyt_route_info.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Init the Google Map and set the listeners.
        mMap = googleMap;
        enableMyLocation();
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnMapLoadedCallback(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "connection failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lyt_back:
                MapActivity.this.finish();
                break;
            case R.id.lyt_right:
                //Open the Google Maps
                MapUtil.intentToGoogleMap(MapActivity.this, response.getRoutes().get(0));
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        //Go to route detail page.
        Intent intent = new Intent(MapActivity.this, RouteDetailActivity.class);
        intent.putExtra(Constants.KEY_INTENT_LEG, response.getRoutes().get(0).getLegs().get(position) );
        startActivity(intent);
    }

    @Override
    public void OnPageChanged(int i, int i1) {
        currentLeg = i1;
        mapPresenter.drawLegRoute(mMap, response, i1);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (View.VISIBLE == lyt_route_info.getVisibility()){
            lyt_route_info.startAnimation(animation_out);
        }else {
            lyt_route_info.startAnimation(animation_in);
        }
        if (View.VISIBLE == rvp_routes.getVisibility()){
            if (mapPresenter.drawMarkerAndRoute(mMap, response)){
                rvp_routes.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        if (rvp_routes.getVisibility() == View.VISIBLE){
            if (mapPresenter.drawMarkerAndRoute(mMap, response)){
                rvp_routes.setVisibility(View.GONE);
            }
        }else {
            if (mapPresenter.drawLegRoute(mMap, response, currentLeg)){
                rvp_routes.setVisibility(View.VISIBLE);
            }
        }
        lyt_route_info.setVisibility(View.GONE);
    }

    @Override
    public void onMapLoaded() {
        isLoaded = true;
        if(response != null) {
            MapUtil.displayArea(mMap, this, response.getRoutes().get(0).getBounds(), 40);
        }
    }

    @Override
    public void routeResponse(RouteResponse response) {
        this.response = response;
        if(isLoaded) {
            MapUtil.displayArea(mMap, this, response.getRoutes().get(0).getBounds(), 40);
        }
        rvp_routes.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
        LegRouteAdapter adapter = new LegRouteAdapter(this, response.getRoutes().get(0).getLegs());
        adapter.setOnItemClickListener(this);
        rvp_routes.addOnPageChangedListener(this);
        rvp_routes.setAdapter(adapter);

        tv_num_markers.setText("There are " +  response.getRoutes().get(0).getLegs().size() + " delivery locations");
        int[] distanceAndDuration = mapPresenter.getDurationAndDistance(response);
        tv_duration.setText("The estimated time spent: " + GeneralUtil.secondToMinutes(distanceAndDuration[1]));
        tv_distance.setText("The distance: " + GeneralUtil.mToKm(distanceAndDuration[0]));

        mapPresenter.drawMarkerAndRoute(mMap, response);
    }

    @Override
    public void routeResponseError() {
        Toast.makeText(this, "Routes request failed", Toast.LENGTH_SHORT).show();
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

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtil.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
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
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location.toString(), Toast.LENGTH_LONG).show();
    }
}
