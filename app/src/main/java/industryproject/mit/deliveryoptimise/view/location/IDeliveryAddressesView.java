package industryproject.mit.deliveryoptimise.view.location;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryAddress;

/**
 * The view is related with delivery addresses.
 */
public interface IDeliveryAddressesView {
    /**
     * The callback of getting delivery addresses request.
     * @param addresses the delivery address list
     * @param message response message
     */
    void getAddressesResult(List<DeliveryAddress> addresses, String message);

    /**
     * The callback of getting delivery address request.
     * @param address the delivery address
     * @param message response message
     */
    void getAddressResult(DeliveryAddress address, String message);

    /**
     * The callback of sending departure request.
     * @param address
     * @param message
     */
    void departureResult(DeliveryAddress address, String message);

    /**
     * The callback of sending arrived request.
     * @param address
     * @param message
     */
    void arrivedResult(DeliveryAddress address, String message);
}
