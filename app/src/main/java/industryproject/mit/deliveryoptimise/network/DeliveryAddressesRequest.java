package industryproject.mit.deliveryoptimise.network;

import android.content.Context;

import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import industryproject.mit.deliveryoptimise.network.service.AddressService;
import io.reactivex.Single;

public class DeliveryAddressesRequest extends BaseRequest<AddressService, DeliveryAddressesResponse, DeliveryAddressesResponse> {

    public DeliveryAddressesRequest(Context context) {
        super(context);
    }

    @Override
    protected Map<String, String> getHeader() {
        return null;
    }

    @Override
    protected Class<AddressService> getEndpointClass() {
        return AddressService.class;
    }

    @Override
    protected Single<DeliveryAddressesResponse> getEndpoint(AddressService endpoint) {
        return endpoint.getDeliveryAddresses();
    }

    @Override
    protected DeliveryAddressesResponse dealWithResponse(DeliveryAddressesResponse response) {
        return response;
    }
}
