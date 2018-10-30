package industryproject.mit.deliveryoptimise.entities.parcel;

import java.io.Serializable;

import industryproject.mit.deliveryoptimise.Constants;

/**
 * The destination entity.
 */
public class Destination extends UAddress implements Serializable{
    private int type;
    private String type_name;

    public Destination() {
    }

    public Destination(String city, String suburb, String street) {
        super(city, suburb, street);
        this.type = Constants.TYPE_LOCATION_DESTINATION;
        this.type_name = Constants.TYPE_NAME_DESTINATION;
    }

    public int getType() {
        return type;
    }

    public String getType_name() {
        return type_name;
    }
}
