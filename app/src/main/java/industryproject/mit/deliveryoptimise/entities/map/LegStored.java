package industryproject.mit.deliveryoptimise.entities.map;

import java.util.List;

/**
 * For storing the data and real status of every route in local.
 */
public class LegStored extends LegInfo {
    private long depart_time;
    private long arrive_time;
    private int spent_time;
    private int status;

    public LegStored() {
    }

    public LegStored(Distance distance, Duration duration, String end_address, Location end_location, String start_address, Location start_location, List<StepInfo> steps) {
        super(distance, duration, end_address, end_location, start_address, start_location, steps);
        status = 0;
    }

    public LegStored getLegRecorder(LegInfo legInfo){
        return new LegStored(legInfo.getDistance(), legInfo.getDuration(), legInfo.getEnd_address(),
                legInfo.getEnd_location(), legInfo.getStart_address(), legInfo.getStart_location(), legInfo.getSteps());
    }

    public long getDepart_time() {
        return depart_time;
    }

    public void setDepart_time(long depart_time) {
        this.depart_time = depart_time;
    }

    public long getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(long arrive_time) {
        this.arrive_time = arrive_time;
    }

    public int getSpent_time() {
        return spent_time;
    }

    public void setSpent_time(int spent_time) {
        this.spent_time = spent_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
