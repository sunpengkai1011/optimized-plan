package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;
import industryproject.mit.deliveryoptimise.entities.parcel.WayPoint;

/**
 * For create the route request info.
 */
public class RouteRequestInfo implements Serializable {
    private String origin;
    private String destination;
    private List<WayPoint> way_points;

    public RouteRequestInfo() {
    }

    public RouteRequestInfo(String origin, String destination, List<WayPoint> way_points) {
        this.origin = origin;
        this.destination = destination;
        this.way_points = way_points;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<WayPoint> getWayPoints() {
        return way_points;
    }

    public void setWayPoints(List<WayPoint> way_points) {
        this.way_points = way_points;
    }

    public String getWaypointsStr(){
        String way_point = "optimize:true|";
        for (WayPoint item: way_points){
            way_point = way_point + item.toString() + "|";
        }
        String result = way_point.substring(0, way_point.length()-1);
        result = result.replace(", ", "+");
        result = result.replace(" ", "+");
        return result;
    }
}
