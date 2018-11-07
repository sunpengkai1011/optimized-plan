package industryproject.mit.deliveryoptimise.view.location;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryAddress;

public interface IDeliveryAddressesView {
    void getAddressesResult(List<DeliveryAddress> addresses, String message);
}
