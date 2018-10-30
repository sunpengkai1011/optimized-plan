package industryproject.mit.deliveryoptimise.entities.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * For storing the data of every route in local
 */
public class RouteStored implements Serializable{
    List<LegStored> legStoreds;

    public RouteStored() {
    }

    public RouteStored(List<LegStored> legStoreds) {
        this.legStoreds = legStoreds;
    }

    public List<LegStored> getLegStoreds() {
        return legStoreds;
    }

    public void setLegStoreds(List<LegStored> legStoreds) {
        this.legStoreds = legStoreds;
    }

    public void putLegStoreds(List<LegInfo> legInfos){
        if (legStoreds == null){
            legStoreds = new ArrayList<>();
        }
        for (LegInfo info : legInfos){
            legStoreds.add(new LegStored().getLegRecorder(info));
        }
    }
}
