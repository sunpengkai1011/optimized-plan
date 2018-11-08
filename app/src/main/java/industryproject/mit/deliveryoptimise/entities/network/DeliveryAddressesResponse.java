package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryAddress;

/**
 * The response of getting delivery addresses entity
 */
public class DeliveryAddressesResponse extends BaseResponse implements Serializable {
    List<DeliveryAddress> addresses;//Delivery address list

    public List<DeliveryAddress> getDeliveryAddress() {
        return addresses;
    }

    public void setDeliveryAddress(List<DeliveryAddress> deliveryAddress) {
        this.addresses = deliveryAddress;
    }
}
