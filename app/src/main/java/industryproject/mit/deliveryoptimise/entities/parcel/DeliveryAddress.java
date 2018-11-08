package industryproject.mit.deliveryoptimise.entities.parcel;

import android.text.TextUtils;
import android.view.TextureView;

import java.io.Serializable;

/**
 * The address entity
 */
public class DeliveryAddress implements Serializable{
    private int id;
    private String city;
    private String suburb;
    private String street;
    private int type_id;
    private String type_name;
    private int status;
    private long departure_time, arrived_time;
    private int spent_time;

    public int getId() {
        return id;
    }

    public int getType_id() {
        return type_id;
    }

    public long getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(long departure_time) {
        this.departure_time = departure_time;
    }

    public long getArrived_time() {
        return arrived_time;
    }

    public void setArrived_time(long arrived_time) {
        this.arrived_time = arrived_time;
    }

    public int getSpent_time() {
        return spent_time;
    }

    public void setSpent_time(int spent_time) {
        this.spent_time = spent_time;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (TextUtils.isEmpty(city)){
            city = "";
        }
        this.city = city;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        if (TextUtils.isEmpty(suburb)){
            suburb = "";
        }
        this.suburb = suburb;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (TextUtils.isEmpty(street)){
            street = "";
        }
        this.street = street;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String address = "";
        if (!TextUtils.isEmpty(city) && !TextUtils.isEmpty(suburb) && !TextUtils.isEmpty(street)){
            address = street + ", " + suburb + ", " + city;
        }else {
            if (!TextUtils.isEmpty(city) && !TextUtils.isEmpty(suburb)){
                address = suburb + ", " + city;
            }
            if (!TextUtils.isEmpty(city) && !TextUtils.isEmpty(street)){
                address = street + ", " + city;
            }
        }
        return address;
    }
}
