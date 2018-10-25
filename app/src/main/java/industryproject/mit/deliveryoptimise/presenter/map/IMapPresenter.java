package industryproject.mit.deliveryoptimise.presenter.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import okhttp3.Route;

public interface IMapPresenter {
    void requestOptimiseRoutes(List<UAddress> addresses);
    int[] getDurationAndDistance(RouteResponse response);
    boolean drawMarkerAndRoute(GoogleMap mMap, RouteResponse response);
    boolean drawLegRoute(GoogleMap mMap, RouteResponse response, int position);
}
