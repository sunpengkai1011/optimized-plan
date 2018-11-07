package industryproject.mit.deliveryoptimise.presenter.address;

import android.content.Context;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.entities.network.DeliveryAddressesResponse;
import industryproject.mit.deliveryoptimise.model.location.DeliveryLocationModel;
import industryproject.mit.deliveryoptimise.model.location.IDeliveryLocation;
import industryproject.mit.deliveryoptimise.view.location.IDeliveryAddressesView;

/**
 * The implementation of "IDeliveryAddressPresenter"
 */
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
        //Determine whether the response is empty, and call the corresponding method.
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

    @Override
    public void getDeliveryAddress(int id) {
        iDeliveryLocation = new DeliveryLocationModel(context, iDeliveryAddressesView);
        iDeliveryLocation.getDeliveryAddress(id);
    }

    @Override
    public void getAddressResult(DeliveryAddressesResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iDeliveryAddressesView.getAddressResult(null, "Get delivery addresses failed");
        }else {
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()){
                iDeliveryAddressesView.getAddressResult(response.getDeliveryAddress().get(0), response.getMessage());
            }else{
                iDeliveryAddressesView.getAddressResult(null, response.getMessage());
            }
        }
    }

    @Override
    public void requsetDeparture(int id) {
        iDeliveryLocation = new DeliveryLocationModel(context, iDeliveryAddressesView);
        iDeliveryLocation.requsetDeparture(id);
    }

    @Override
    public void requestArrived(int id) {
        iDeliveryLocation = new DeliveryLocationModel(context, iDeliveryAddressesView);
        iDeliveryLocation.requestArrived(id);
    }

    @Override
    public void departureResult(DeliveryAddressesResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iDeliveryAddressesView.departureResult(null, "Get delivery addresses failed");
        }else {
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()){
                iDeliveryAddressesView.departureResult(response.getDeliveryAddress().get(0), response.getMessage());
            }else{
                iDeliveryAddressesView.departureResult(null, response.getMessage());
            }
        }
    }

    @Override
    public void arrivedResult(DeliveryAddressesResponse response) {
        //Determine whether the response is empty, and call the corresponding method.
        if (response == null){
            iDeliveryAddressesView.arrivedResult(null, "Get delivery addresses failed");
        }else {
            if (Constants.RESPONSE_CODE_SUCCESSFUL == response.getCode()){
                iDeliveryAddressesView.arrivedResult(response.getDeliveryAddress().get(0), response.getMessage());
            }else{
                iDeliveryAddressesView.arrivedResult(null, response.getMessage());
            }
        }
    }
}
