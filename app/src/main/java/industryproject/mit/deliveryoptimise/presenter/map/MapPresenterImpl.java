package industryproject.mit.deliveryoptimise.presenter.map;

import android.content.Context;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.RouteResponse;
import industryproject.mit.deliveryoptimise.entities.UAddress;
import industryproject.mit.deliveryoptimise.model.map.IMap;
import industryproject.mit.deliveryoptimise.model.map.MapModel;
import industryproject.mit.deliveryoptimise.network.RequestCallBack;
import industryproject.mit.deliveryoptimise.view.map.IMapView;

public class MapPresenterImpl implements IMapPresenter, RequestCallBack{
    private IMap iMap;
    private IMapView iMapView;

    public MapPresenterImpl(Context context, IMapView iMapView) {
        this.iMapView = iMapView;
        iMap = new MapModel(context, this);
    }

    @Override
    public void requestOptimiseRoutes(List<UAddress> addresses) {
        iMap.requestOptimiseRoutes(addresses);
    }

    @Override
    public void requestCallBack(Object o) {
        iMapView.routeResponse((RouteResponse) o);
    }

    @Override
    public void requestFailure(Object o) {
        iMapView.routeResponseError();
    }
}
