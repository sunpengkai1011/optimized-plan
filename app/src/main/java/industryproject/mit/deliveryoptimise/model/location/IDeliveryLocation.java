package industryproject.mit.deliveryoptimise.model.location;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

/**
 * The interface of location. It is for MVP pattern.
 */
public interface IDeliveryLocation {
    DeliveryLocations getDeliveryLocations(String userId);
}
