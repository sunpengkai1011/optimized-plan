package industryproject.mit.deliveryoptimise.network.service;

import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import io.reactivex.Single;
import retrofit2.http.POST;

/**
 * The request service for getting addresses.
 */
public interface AddressService {
    @POST("address")
    Single<DeliveryAddressesResponse> getDeliveryAddresses();

    @POST("address/item")
    Single<DeliveryAddressesResponse> getDeliveryAddress(int id);

    @POST("address/departure")
    Single<DeliveryAddressesResponse> requestDeparture(int id);

    @POST("address/arrived")
    Single<DeliveryAddressesResponse> requestArrived(int id);
}
