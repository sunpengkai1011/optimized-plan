package industryproject.mit.deliveryoptimise.view.location;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.UAddress;

public interface IDeliveryLocationView {
    void getDeliveryLocations(List<UAddress> addresses);

    void getDeliveryLocationsError();
}
