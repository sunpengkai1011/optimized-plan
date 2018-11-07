package industryproject.mit.deliveryoptimise.entities.parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeliveryLocations implements Serializable{
    private DeliveryAddress origin;
    private DeliveryAddress destination;
    private List<DeliveryAddress> wayPoints;

    public DeliveryLocations() {
        wayPoints = new ArrayList<>();
    }

    public DeliveryAddress getOrigin() {
        return origin;
    }

    public void setOrigin(DeliveryAddress origin) {
        this.origin = origin;
    }

    public DeliveryAddress getDestination() {
        return destination;
    }

    public void setDestination(DeliveryAddress destination) {
        this.destination = destination;
    }

    public List<DeliveryAddress> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<DeliveryAddress> wayPoints) {
        this.wayPoints = wayPoints;
    }
}
