package industryproject.mit.deliveryoptimise.entities;

import java.io.Serializable;
import java.util.List;

public class RouteResponse implements Serializable{
    private List<RouteInfo> routes;

    public List<RouteInfo> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteInfo> routes) {
        this.routes = routes;
    }
}