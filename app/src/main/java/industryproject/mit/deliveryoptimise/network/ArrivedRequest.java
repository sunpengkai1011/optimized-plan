package industryproject.mit.deliveryoptimise.network;

import android.content.Context;

import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.network.AddressTime;
import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import industryproject.mit.deliveryoptimise.network.service.AddressService;
import io.reactivex.Single;

/**
 * The departure request.
 */
public class ArrivedRequest extends BaseRequest<AddressService, DeliveryAddressesResponse, DeliveryAddressesResponse> {
    private AddressTime addressTime;

    public ArrivedRequest(Context context, AddressTime addressTime) {
        super(context);
        this.addressTime = addressTime;
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
        return endpoint.requestArrived(addressTime);
    }

    @Override
    protected DeliveryAddressesResponse dealWithResponse(DeliveryAddressesResponse response) {
        return response;
    }
}
