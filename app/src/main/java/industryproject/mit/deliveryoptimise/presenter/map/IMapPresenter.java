package industryproject.mit.deliveryoptimise.presenter.map;

import com.google.android.gms.maps.GoogleMap;

import industryproject.mit.deliveryoptimise.entities.map.RouteStored;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

public interface IMapPresenter {
    void requestOptimiseRoutes(DeliveryLocations locations);
    int[] getDurationAndDistance(RouteResponse response);
    boolean drawMarkerAndRoute(GoogleMap mMap, RouteResponse response);
    boolean drawLegRoute(GoogleMap mMap, RouteResponse response, int position);
    void departedTimeStored(long departed_time, int position);
    void arrivedTimeStored(long arrived_time, int position);
    void routeStored(RouteResponse response);
    RouteStored refreshRouteStored();
}
