package industryproject.mit.deliveryoptimise.network.service;

import industryproject.mit.deliveryoptimise.entities.network.AddressTime;
import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * The request service for getting addresses.
 */
public interface AddressService {
    @POST("address")
    Single<DeliveryAddressesResponse> getDeliveryAddresses();

    @GET("address/{itemid}")
    Single<DeliveryAddressesResponse> getDeliveryAddress(@Path("itemid") int id);

    @PUT("address/departure")
    Single<DeliveryAddressesResponse> requestDeparture(@Body AddressTime addressTime);

    @PUT("address/arrived")
    Single<DeliveryAddressesResponse> requestArrived(@Body AddressTime addressTime);
}
