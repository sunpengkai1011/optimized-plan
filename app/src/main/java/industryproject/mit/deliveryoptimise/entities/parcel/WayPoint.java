package industryproject.mit.deliveryoptimise.entities.parcel;

import java.io.Serializable;

import industryproject.mit.deliveryoptimise.Constants;

public class WayPoint extends UAddress implements Serializable{
    private int type;
    private String type_name;

    public WayPoint() {
    }

    public WayPoint(String city, String suburb, String street) {
        super(city, suburb, street);
        this.type = Constants.TYPE_LOCATION_WAYPOINT;
        this.type_name = Constants.TYPE_NAME_WAYPOINT;
    }

    public int getType() {
        return type;
    }

    public String getType_name() {
        return type_name;
    }
}
