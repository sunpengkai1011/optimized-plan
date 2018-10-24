package industryproject.mit.deliveryoptimise.model.map;

import java.util.List;

import industryproject.mit.deliveryoptimise.entities.UAddress;

public interface IMap {
    void requestOptimiseRoutes(List<UAddress> addresses);
}
