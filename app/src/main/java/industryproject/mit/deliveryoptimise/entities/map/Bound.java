package industryproject.mit.deliveryoptimise.entities.map;

import java.io.Serializable;

/**
 * For storing the display area data of map.
 */
public class Bound implements Serializable{
    private Location northeast;
    private Location southwest;

    public Location getNortheast() {
        return northeast;
    }

    public void setNortheast(Location northeast) {
        this.northeast = northeast;
    }

    public Location getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Location southwest) {
        this.southwest = southwest;
    }
}
