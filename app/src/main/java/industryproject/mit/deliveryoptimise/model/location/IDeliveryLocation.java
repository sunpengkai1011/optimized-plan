package industryproject.mit.deliveryoptimise.model.location;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;

public interface IDeliveryLocation {
    DeliveryLocations getDeliveryLocations(String userId);
}
