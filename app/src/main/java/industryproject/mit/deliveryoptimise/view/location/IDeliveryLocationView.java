package industryproject.mit.deliveryoptimise.view.location;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;

public interface IDeliveryLocationView {
    void getDeliveryLocations(DeliveryLocations locations);

    void getDeliveryLocationsError();
}
