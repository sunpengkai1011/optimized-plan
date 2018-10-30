package industryproject.mit.deliveryoptimise.model.map;

import industryproject.mit.deliveryoptimise.entities.map.RouteStored;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

/**
 * The interface of map. It is for MVP pattern.
 */
public interface IMap {
    /**
     * Get the optimised routes.
     * @param locations all delivery locations
     */
    void requestOptimiseRoutes(DeliveryLocations locations);

    /**
     * storing the departed time.
     * @param departed_time
     * @param position the position of leg.
     * @return
     */
    boolean departedTimeStored(long departed_time, int position);

    /**
     * Storing the arrived time.
     * @param arrived_time
     * @param position the position of leg.
     * @return
     */
    boolean arrivedTimeStored(long arrived_time, int position);

    /**
     * Storing the data of routes in local.
     * @param response the response from Google Map Api.
     * @return data of routes.
     */
    RouteStored routeStored(RouteResponse response);

    /**
     * Refreshing the data of routes.
     * @return data of routes.
     */
    RouteStored refreshRouteStored();
}
