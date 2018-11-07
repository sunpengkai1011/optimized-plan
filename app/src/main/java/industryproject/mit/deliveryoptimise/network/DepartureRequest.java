package industryproject.mit.deliveryoptimise.network;

import android.content.Context;

import java.util.Map;

import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import industryproject.mit.deliveryoptimise.network.service.AddressService;
import io.reactivex.Single;

/**
 * The departure request.
 */
public class DepartureRequest extends BaseRequest<AddressService, DeliveryAddressesResponse, DeliveryAddressesResponse> {
    private int id;

    public DepartureRequest(Context context, int id) {
        super(context);
        this.id = id;
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
        return endpoint.requestDeparture(id);
    }

    @Override
    protected DeliveryAddressesResponse dealWithResponse(DeliveryAddressesResponse response) {
        return response;
    }
}
