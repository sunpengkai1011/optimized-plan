package industryproject.mit.deliveryoptimise.model.map;

import industryproject.mit.deliveryoptimise.entities.map.RouteStored;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

public interface IMap {
    void requestOptimiseRoutes(DeliveryLocations locations);
    boolean departedTimeStored(long departed_time, int position);
    boolean arrivedTimeStored(long arrived_time, int position);
    RouteStored routeStored(RouteResponse response);
    RouteStored refreshRouteStored();
}
