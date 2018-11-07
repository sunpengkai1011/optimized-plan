package industryproject.mit.deliveryoptimise.model.location;


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
     * @param id address id
     */
    void requsetDeparture(int id);

    /**
     * The arrived request
     * @param id address id
     */
    void requestArrived(int id);
}
