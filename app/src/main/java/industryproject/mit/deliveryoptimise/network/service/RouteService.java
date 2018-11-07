package industryproject.mit.deliveryoptimise.network.service;

import industryproject.mit.deliveryoptimise.entities.network.RouteResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RouteService {

    @GET("maps/api/directions/json")
    Call<RouteResponse> getRouteInfo(@Query("origin") String origin,
                                     @Query("destination") String destination,
                                     @Query("waypoints") String waypoints,
                                     @Query("departure_time") String departure_time,
                                     @Query("key") String key,
                                     @Query("optimizeWaypoints") boolean optimizeWaypoints);
}
