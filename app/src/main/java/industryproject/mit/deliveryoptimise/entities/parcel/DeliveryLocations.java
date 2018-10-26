package industryproject.mit.deliveryoptimise.entities.parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeliveryLocations implements Serializable{
    private Origin origin;
    private Destination destination;
    private List<WayPoint> wayPoints;

    public DeliveryLocations(Origin origin, Destination destination, List<WayPoint> wayPoints) {
        this.origin = origin;
        this.destination = destination;
        this.wayPoints = wayPoints;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }
}
