package industryproject.mit.deliveryoptimise.model.location;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.Constants;
import industryproject.mit.deliveryoptimise.R;
import industryproject.mit.deliveryoptimise.entities.RouteRequestInfo;
import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.network.RouteRequest;
import okhttp3.Route;

public class DeliveryLocationModel implements IDeliveryLocation {
    private Context context;

    public DeliveryLocationModel(Context context) {
        this.context = context;
    }

    @Override
    public List<UAddress> getDeliveryLocations(String userId) {
        return testData();
    }

    private List<UAddress> testData(){
        List<UAddress> addresses = new ArrayList<>();
        UAddress uAddress = new UAddress("Auckland", "Manukau City", "Manukau Station", Constants.TYPE_LOCATION_ORIGIN, context.getResources().getString(R.string.name_origin));
        UAddress uAddress1 = new UAddress("Auckland", "Manukau City", "Manukau Station", Constants.TYPE_LOCATION_DESTINATION, context.getResources().getString(R.string.name_destination));
        UAddress uAddress2 = new UAddress("Auckland", "", "Airport", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        UAddress uAddress3 = new UAddress("Auckland", "", "Botany Town Center", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        UAddress uAddress4 = new UAddress("Auckland", "", "Mt Eden", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        UAddress uAddress5 = new UAddress("Auckland", "", "Mission Bay", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        UAddress uAddress6 = new UAddress("Auckland", "", "One Tree Hill", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        UAddress uAddress7 = new UAddress("Auckland", "", "Sylvia Park Station", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        UAddress uAddress8 = new UAddress("Auckland", "", "New Market Station", Constants.TYPE_LOCATION_WAYPOINT, context.getResources().getString(R.string.name_waypoint));
        addresses.add(uAddress);
        addresses.add(uAddress1);
        addresses.add(uAddress2);
        addresses.add(uAddress3);
        addresses.add(uAddress4);
        addresses.add(uAddress5);
        addresses.add(uAddress6);
        addresses.add(uAddress7);
        addresses.add(uAddress8);
        return addresses;
    }
}
