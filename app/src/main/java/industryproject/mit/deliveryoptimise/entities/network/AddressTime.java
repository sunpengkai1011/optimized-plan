package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;

public class AddressTime implements Serializable{
    private int id;
    private long time;

    public AddressTime(int id, long time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
