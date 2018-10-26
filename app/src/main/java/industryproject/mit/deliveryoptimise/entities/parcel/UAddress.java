package industryproject.mit.deliveryoptimise.entities.parcel;

import android.text.TextUtils;
import android.view.TextureView;

import java.io.Serializable;

public class UAddress implements Serializable{
    private String city;
    private String suburb;
    private String street;

    public UAddress() {
    }

    public UAddress(String city, String suburb, String street) {
        this.city = city;
        this.suburb = suburb;
        this.street = street;
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
