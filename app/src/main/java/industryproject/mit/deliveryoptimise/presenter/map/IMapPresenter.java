package industryproject.mit.deliveryoptimise.presenter.map;

import com.google.android.gms.maps.GoogleMap;

import industryproject.mit.deliveryoptimise.entities.map.RouteStored;
import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

public interface IMapPresenter {
    /**
     * The request of getting the optimised routes.
     * @param locations the delivery addresses
     */
    void requestOptimiseRoutes(DeliveryLocations locations);

    /**
     * Get the total duration and distance of all routes.
     * @param response the response of optimised routes
     * @return
     */
    int[] getDurationAndDistance(RouteResponse response);

    /**
     * Draw the markers and routes
     * @param mMap map entity
     * @param response the response of optimised routes
     * @return
     */
    boolean drawMarkerAndRoute(GoogleMap mMap, RouteResponse response);

    /**
     * Draw the a route.
     * @param mMap map entity
     * @param response the response of optimised routes
     * @param position position in route response list.
     * @return
     */
    boolean drawLegRoute(GoogleMap mMap, RouteResponse response, int position);
}
