package industryproject.mit.deliveryoptimise.presenter.address;

import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;

/**
 * The presenter which related with delivery addresses
 */
public interface IDeliveryAddressPresenter {

    /**
     * The request of getting delivery address list
     */
    void getDeliveryAddresses();

    /**
     * The callback of getting delivery address list
     * @param response the response of getting delivery addresses information
     */
    void getAddressesResult(DeliveryAddressesResponse response);

    /**
     * The request of getting delivery address
     * @param id address id
     */
    void getDeliveryAddress(int id);

    /**
     * The callback of getting delivery address
     * @param response the response of getting delivery addresses information
     */
    void getAddressResult(DeliveryAddressesResponse response);

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

    /**
     * The callback of sending departure request.
     * @param response the response of getting delivery address information
     */
    void departureResult(DeliveryAddressesResponse response);

    /**
     * The callback of sending arrived request.
     * @param response the response of getting delivery address information
     */
    void arrivedResult(DeliveryAddressesResponse response);
}
