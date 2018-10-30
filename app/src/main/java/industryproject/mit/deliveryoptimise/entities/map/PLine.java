package industryproject.mit.deliveryoptimise.entities.map;

import java.io.Serializable;

/**
 * For storing the real data of every route.
 */
public class PLine implements Serializable {
    String points;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
