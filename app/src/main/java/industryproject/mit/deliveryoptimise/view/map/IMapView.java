package industryproject.mit.deliveryoptimise.view.map;

import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;

public interface IMapView {
    void routeResponse(RouteResponse response);
    void routeResponseError();
}
