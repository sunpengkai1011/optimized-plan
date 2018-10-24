package industryproject.mit.deliveryoptimise.model.map;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.RouteRequestInfo;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.network.RouteRequest;

public class MapModel implements IMap {
    private Context context;
    private RequestCallBack requestCallBack;

    public MapModel(Context context, RequestCallBack requestCallBack) {
        this.context = context;
        this.requestCallBack = requestCallBack;
    }
    @Override
    public void requestOptimiseRoutes(List<UAddress> addresses) {
        RouteRequestInfo info = new RouteRequestInfo();
        List<UAddress> way_points = new ArrayList<>();
        for (UAddress address : addresses){
            switch (address.getType()){
                case Constants.TYPE_LOCATION_ORIGIN:
                    info.setOrigin(address.toString());
                    break;
                case Constants.TYPE_LOCATION_DESTINATION:
                    info.setDestination(address.toString());
                    break;
                case Constants.TYPE_LOCATION_WAYPOINT:
                    way_points.add(address);
                    break;
            }
        }
        info.setWayPoints(way_points);
        RouteRequest request = new RouteRequest(context, requestCallBack, info.getOrigin(), info.getDestination(), info.getWaypointsStr());
        request.getRequest();
    }
}
