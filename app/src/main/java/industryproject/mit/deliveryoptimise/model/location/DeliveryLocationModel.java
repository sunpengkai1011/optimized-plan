package industryproject.mit.deliveryoptimise.model.location;

import android.content.Context;

import industryproject.mit.deliveryoptimise.network.DeliveryAddressesRequest;
import industryproject.mit.deliveryoptimise.presenter.address.DeliveryAddressPresenterImpl;
import industryproject.mit.deliveryoptimise.view.location.IDeliveryAddressesView;

/**
 * It is the Model in MVP pattern. For get the data about the delivery locations.
 */
public class DeliveryLocationModel implements IDeliveryLocation {
    private Context context;
    private DeliveryAddressPresenterImpl presenter;

    public DeliveryLocationModel(Context context, IDeliveryAddressesView addressesView) {
        this.context = context;
        presenter = new DeliveryAddressPresenterImpl(context, addressesView);
    }

    @Override
    public void getDeliveryAddresses() {
        new DeliveryAddressesRequest(context).getData().subscribe(deliveryAddressesResponse -> presenter.getAddressesResult(deliveryAddressesResponse), e -> presenter.getAddressesResult(null));
    }
}
