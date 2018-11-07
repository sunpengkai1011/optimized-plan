package industryproject.mit.deliveryoptimise.network.service;

import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import io.reactivex.Single;
import retrofit2.http.POST;

public interface AddressService {

    @POST("address")
    Single<DeliveryAddressesResponse> getDeliveryAddresses();
}
