package industryproject.mit.deliveryoptimise.presenter.map;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.UAddress;

public interface IMapPresenter {
    void requestOptimiseRoutes(List<UAddress> addresses);
}
