package industryproject.mit.deliveryoptimise.model.map;

import android.content.Context;

import industryproject.mit.deliveryoptimise.entities.network.RouteRequestInfo;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.network.RouteRequest;

/**
 * It is the Model in MVP pattern. For operating the data about the map.
 */
public class MapModel implements IMap {
    private Context context;
    private RequestCallBack requestCallBack;// the call back of the route request.

    public MapModel(Context context, RequestCallBack requestCallBack) {
        this.context = context;
        this.requestCallBack = requestCallBack;
    }
    @Override
    public void requestOptimiseRoutes(DeliveryLocations locations) {
        //Send the route request by Google Map Api.
        RouteRequestInfo info = new RouteRequestInfo(locations.getOrigin().toString(), locations.getDestination().toString(), locations.getWayPoints());
        RouteRequest request = new RouteRequest(context, requestCallBack, info.getOrigin(), info.getDestination(), info.getWaypointsStr());
        request.getRequest();
    }
}
