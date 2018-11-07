package industryproject.mit.deliveryoptimise.model.map;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.map.RouteStored;
import industryproject.mit.deliveryoptimise.entities.network.RouteRequestInfo;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.network.RouteRequest;
import industryproject.mit.deliveryoptimise.utils.GeneralUtil;

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

    @Override
    public boolean departedTimeStored(long departed_time, int position) {
        if (departed_time != 0) {
            //Get the data of routes.
            RouteStored routeStored = GeneralUtil.fromJson(GeneralUtil.getDataFromSP(context, Constants.SP_KEY_ROUTES), RouteStored.class);
            //Storing the departure time in the corresponding route data
            routeStored.getLegStoreds().get(position).setDepart_time(departed_time);
            //Modify the status of this route data.
            routeStored.getLegStoreds().get(position).setStatus(Constants.STATUS_DEPARTED);
            //Storing the new data in local.
            GeneralUtil.storDataBySP(context, Constants.SP_KEY_ROUTES, GeneralUtil.toJson(routeStored, RouteStored.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean arrivedTimeStored(long arrived_time, int position) {
        if (arrived_time != 0) {
            //Get the data of routes.
            RouteStored routeStored = GeneralUtil.fromJson(GeneralUtil.getDataFromSP(context, Constants.SP_KEY_ROUTES), RouteStored.class);
            //Storing the arrived time in the corresponding route data
            routeStored.getLegStoreds().get(position).setArrive_time(arrived_time);
            //Modify the status of this route data.
            routeStored.getLegStoreds().get(position).setStatus(Constants.STATUS_ARRIVED);
            //Calculating the actually time spent of the route.
            routeStored.getLegStoreds().get(position).setSpent_time(GeneralUtil.calculateTime(routeStored.getLegStoreds().get(position).getDepart_time(), arrived_time));
            //Storing the new data in local.
            GeneralUtil.storDataBySP(context, Constants.SP_KEY_ROUTES, GeneralUtil.toJson(routeStored, RouteStored.class));
            return true;
        }
        return false;
    }

    @Override
    public RouteStored routeStored(RouteResponse response) {
        RouteStored routeStored = new RouteStored();
        routeStored.putLegStoreds(response.getRoutes().get(0).getLegs());
        GeneralUtil.storDataBySP(context, Constants.SP_KEY_ROUTES, GeneralUtil.toJson(routeStored, RouteStored.class));
        return routeStored;
    }

    @Override
    public RouteStored refreshRouteStored() {
        return GeneralUtil.fromJson(GeneralUtil.getDataFromSP(context, Constants.SP_KEY_ROUTES), RouteStored.class);
    }
}
