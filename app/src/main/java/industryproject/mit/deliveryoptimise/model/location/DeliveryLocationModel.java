package industryproject.mit.deliveryoptimise.model.location;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import industryproject.mit.deliveryoptimise.entities.parcel.DeliveryLocations;
import industryproject.mit.deliveryoptimise.entities.parcel.Destination;
import industryproject.mit.deliveryoptimise.entities.parcel.Origin;
import industryproject.mit.deliveryoptimise.entities.parcel.WayPoint;

public class DeliveryLocationModel implements IDeliveryLocation {
    private Context context;

    public DeliveryLocationModel(Context context) {
        this.context = context;
    }

    @Override
    public DeliveryLocations getDeliveryLocations(String userId) {
        return testData();
    }

    private DeliveryLocations testData(){
        Origin origin = new Origin("Auckland", "Manukau City", "Manukau Station");
        Destination destination = new Destination("Auckland", "Manukau City", "Manukau Station");
        WayPoint wayPoint = new WayPoint("Auckland", "", "Airport");
        WayPoint wayPoint1 = new WayPoint("Auckland", "", "Botany Town Center");
        WayPoint wayPoint2 = new WayPoint("Auckland", "", "Mt Eden");
        WayPoint wayPoint3 = new WayPoint("Auckland", "", "Mission Bay");
        WayPoint wayPoint4 = new WayPoint("Auckland", "", "One Tree Hill");
        WayPoint wayPoint5 = new WayPoint("Auckland", "", "Sylvia Park Station");
        WayPoint wayPoint6 = new WayPoint("Auckland", "", "New Market Station");
        List<WayPoint> wayPoints = new ArrayList<>();
        wayPoints.add(wayPoint);
        wayPoints.add(wayPoint1);
        wayPoints.add(wayPoint2);
        wayPoints.add(wayPoint3);
        wayPoints.add(wayPoint4);
        wayPoints.add(wayPoint5);
        wayPoints.add(wayPoint6);
        return new DeliveryLocations(origin, destination,wayPoints);
    }
}
