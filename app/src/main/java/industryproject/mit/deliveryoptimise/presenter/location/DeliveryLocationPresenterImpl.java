package industryproject.mit.deliveryoptimise.presenter.location;

import android.content.Context;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;
import industryproject.mit.deliveryoptimise.model.location.DeliveryLocationModel;
import industryproject.mit.deliveryoptimise.model.location.IDeliveryLocation;
import industryproject.mit.deliveryoptimise.view.location.IDeliveryLocationView;

public class DeliveryLocationPresenterImpl implements IDeliveryLocationPresenter {
    private IDeliveryLocation iDeliveryLocation;
    private IDeliveryLocationView iDeliveryLocationView;

    public DeliveryLocationPresenterImpl(Context context, IDeliveryLocationView iDeliveryLocationView) {
        this.iDeliveryLocationView = iDeliveryLocationView;
        iDeliveryLocation = new DeliveryLocationModel(context);
    }

    @Override
    public void getDeliveryLocations(String userId) {
        DeliveryLocations locations = iDeliveryLocation.getDeliveryLocations(userId);
        if (locations != null){
            iDeliveryLocationView.getDeliveryLocations(locations);
        }else{
            iDeliveryLocationView.getDeliveryLocationsError();
        }
    }
}
