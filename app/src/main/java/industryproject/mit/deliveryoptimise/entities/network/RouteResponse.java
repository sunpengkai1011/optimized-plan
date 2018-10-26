package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.map.RouteInfo;

public class RouteResponse implements Serializable{
    private List<RouteInfo> routes;

    public List<RouteInfo> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteInfo> routes) {
        this.routes = routes;
    }
}