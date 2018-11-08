package industryproject.mit.deliveryoptimise.model.map;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;

/**
 * The interface of map. It is for MVP pattern.
 */
public interface IMap {
    /**
     * Get the optimised routes.
     * @param locations all delivery locations
     */
    void requestOptimiseRoutes(DeliveryLocations locations);
}
