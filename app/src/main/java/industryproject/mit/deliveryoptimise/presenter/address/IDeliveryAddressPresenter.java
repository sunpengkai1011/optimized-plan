package industryproject.mit.deliveryoptimise.presenter.address;

import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;

public interface IDeliveryAddressPresenter {

    void getDeliveryAddresses();
    void getAddressesResult(DeliveryAddressesResponse response);
}
