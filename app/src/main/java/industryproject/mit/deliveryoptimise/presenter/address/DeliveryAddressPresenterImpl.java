package industryproject.mit.deliveryoptimise.presenter.address;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import industryproject.mit.deliveryoptimise.model.location.DeliveryLocationModel;
import industryproject.mit.deliveryoptimise.model.location.IDeliveryLocation;
import industryproject.mit.deliveryoptimise.view.location.IDeliveryAddressesView;

public class DeliveryAddressPresenterImpl implements IDeliveryAddressPresenter {
    private Context context;
    private IDeliveryLocation iDeliveryLocation;
    private IDeliveryAddressesView iDeliveryAddressesView;

    public DeliveryAddressPresenterImpl(Context context, IDeliveryAddressesView iDeliveryAddressesView) {
        this.context = context;
        this.iDeliveryAddressesView = iDeliveryAddressesView;
    }


    @Override
    public void getDeliveryAddresses() {
        iDeliveryLocation = new DeliveryLocationModel(context, iDeliveryAddressesView);
        iDeliveryLocation.getDeliveryAddresses();
    }

    @Override
    public void getAddressesResult(DeliveryAddressesResponse response) {
        if (response == null){
            iDeliveryAddressesView.getAddressesResult(null, "Get delivery addresses failed");
        }else {
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()){
                iDeliveryAddressesView.getAddressesResult(response.getDeliveryAddress(), response.getMessage());
            }else{
                iDeliveryAddressesView.getAddressesResult(null, response.getMessage());
            }
        }
    }
}
