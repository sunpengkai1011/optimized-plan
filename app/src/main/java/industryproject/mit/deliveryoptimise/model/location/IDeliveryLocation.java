package industryproject.mit.deliveryoptimise.model.location;


import industryproject.mit.deliveryoptimise.entities.network.AddressTime;

/**
 * The interface of location. It is for MVP pattern.
 */
public interface IDeliveryLocation {
    /**
     * The request to get delivery address list
     */
    void getDeliveryAddresses();
    /**
     * The request to get delivery address
     * @param id address id
     */
    void getDeliveryAddress(int id);

    /**
     * The departure request
     * @param addressTime
     */
    void requestDeparture(AddressTime addressTime);

    /**
     * The arrived request
     * @param addressTime
     */
    void requestArrived(AddressTime addressTime);
}
