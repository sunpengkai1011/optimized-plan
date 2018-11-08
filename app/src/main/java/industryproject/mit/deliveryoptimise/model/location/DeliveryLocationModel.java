package industryproject.mit.deliveryoptimise.model.location;

import android.content.Context;

import industryproject.mit.deliveryoptimise.entities.network.AddressTime;
import industryproject.mit.deliveryoptimise.network.ArrivedRequest;
import industryproject.mit.deliveryoptimise.network.DeliveryAddressRequest;
import industryproject.mit.deliveryoptimise.network.DeliveryAddressesRequest;
import industryproject.mit.deliveryoptimise.network.DepartureRequest;
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
        //start to request the delivery addresses.
        new DeliveryAddressesRequest(context).getData().subscribe(deliveryAddressesResponse -> presenter.getAddressesResult(deliveryAddressesResponse), e -> presenter.getAddressesResult(null));
    }

    @Override
    public void getDeliveryAddress(int id) {
        new DeliveryAddressRequest(context, id).getData().subscribe(
                deliveryAddressesResponse -> presenter.getAddressResult(deliveryAddressesResponse),
                e -> presenter.getAddressResult(null));
    }

    @Override
    public void requestDeparture(AddressTime addressTime) {
        new DepartureRequest(context, addressTime).getData().subscribe(
                deliveryAddressesResponse -> presenter.departureResult(deliveryAddressesResponse),
                e -> presenter.departureResult(null));
    }

    @Override
    public void requestArrived(AddressTime addressTime) {
        new ArrivedRequest(context, addressTime).getData().subscribe(
                deliveryAddressesResponse -> presenter.arrivedResult(deliveryAddressesResponse),
                e -> presenter.arrivedResult(null));
    }
}
