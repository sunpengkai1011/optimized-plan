package industryproject.mit.deliveryoptimise.entities;

import android.text.TextUtils;
import android.view.TextureView;

import java.io.Serializable;

public class UAddress implements Serializable{
    private String city;
    private String suburb;
    private String street;
    private int type;
    private String typeName;

    public UAddress() {
    }

    public UAddress(String city, String suburb, String street) {
        this.city = city;
        this.suburb = suburb;
        this.street = street;
    }

    public UAddress(String city, String suburb, String street, int type, String typeName) {
        this.city = city;
        this.suburb = suburb;
        this.street = street;
        this.type = type;
        this.typeName = typeName;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
