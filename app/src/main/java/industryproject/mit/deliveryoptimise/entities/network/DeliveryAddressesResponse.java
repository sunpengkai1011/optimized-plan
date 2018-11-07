package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryAddress;

public class DeliveryAddressesResponse extends BaseResponse implements Serializable {
    List<DeliveryAddress> addresses;

    public List<DeliveryAddress> getDeliveryAddress() {
        return addresses;
    }

    public void setDeliveryAddress(List<DeliveryAddress> deliveryAddress) {
        this.addresses = deliveryAddress;
    }
}
