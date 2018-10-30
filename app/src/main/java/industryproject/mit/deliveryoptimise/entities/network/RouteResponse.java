package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.map.RouteInfo;

/**
 * For storing the response data from Google Map API.
 */
public class RouteResponse implements Serializable{
    private List<RouteInfo> routes;

    public List<RouteInfo> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteInfo> routes) {
        this.routes = routes;
    }
}