package industryproject.mit.deliveryoptimise.presenter.map;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.LegInfo;
import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import industryproject.mit.deliveryoptimise.model.map.IMap;
import industryproject.mit.deliveryoptimise.model.map.MapModel;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.utils.MapUtil;
import industryproject.mit.deliveryoptimise.view.map.IMapView;

public class MapPresenterImpl implements IMapPresenter, RequestCallBack{
    private IMap iMap;
    private IMapView iMapView;
    private Context context;
    private List<Integer> waypoint_order;

    public MapPresenterImpl(Context context, IMapView iMapView) {
        this.iMapView = iMapView;
        iMap = new MapModel(context, this);
        this.context = context;
    }

    @Override
    public void requestOptimiseRoutes(List<UAddress> addresses) {
        iMap.requestOptimiseRoutes(addresses);
    }

    @Override
    public int[] getDurationAndDistance(RouteResponse response) {
        return getTotalDistanceAndDuration(response);
    }

    @Override
    public boolean drawMarkerAndRoute(GoogleMap mMap, RouteResponse response) {
        if (mMap != null && response != null){
            mMap.clear();
            MapUtil.addMarkers(mMap, response.getRoutes().get(0).getLegs());
            MapUtil.drawRoute(mMap, context, response.getRoutes().get(0).getLegs());
            return true;
        }
        return false;
    }

    @Override
    public boolean drawLegRoute(GoogleMap mMap, RouteResponse response, int position) {
        if (mMap != null && response != null){
            mMap.clear();
            MapUtil.addMarkers(mMap, response.getRoutes().get(0).getLegs());
            MapUtil.drawRoute(mMap, context, response.getRoutes().get(0).getLegs(), position);
            return true;
        }
        return false;
    }

    @Override
    public void requestCallBack(Object o) {
        iMapView.routeResponse((RouteResponse) o);
    }

    @Override
    public void requestFailure(Object o) {
        iMapView.routeResponseError();
    }

    private int[] getTotalDistanceAndDuration(RouteResponse response){
        int totalDuration = 0;
        int totalDistance = 0;
        for(LegInfo info : response.getRoutes().get(0).getLegs()){
            totalDuration += info.getDuration().getValue();
            totalDistance += info.getDistance().getValue();
        }
        return new int[]{totalDistance, totalDuration};
    }
}
