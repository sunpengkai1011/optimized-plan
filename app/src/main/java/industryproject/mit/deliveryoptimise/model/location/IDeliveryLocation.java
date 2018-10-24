package industryproject.mit.deliveryoptimise.model.location;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.UAddress;

public interface IDeliveryLocation {
    List<UAddress> getDeliveryLocations(String userId);
}
