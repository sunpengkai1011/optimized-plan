package industryproject.mit.deliveryoptimise.entities;

import java.io.Serializable;
import java.util.List;

public class RouteRequestInfo implements Serializable {
    private String origin;
    private String destination;
    private List<UAddress> way_points;

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

    public List<UAddress> getWayPoints() {
        return way_points;
    }

    public void setWayPoints(List<UAddress> way_points) {
        this.way_points = way_points;
    }

    public String getWaypointsStr(){
        String way_point = "optimize:true|";
        for (UAddress item: way_points){
            way_point = way_point + item.toString() + "|";
        }
        String result = way_point.substring(0, way_point.length()-1);
        result = result.replace(" ", "+");
        result = result.replace(",", "+");
        return result;
    }
}
