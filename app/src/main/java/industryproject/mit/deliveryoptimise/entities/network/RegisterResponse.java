package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;

import industryproject.mit.deliveryoptimise.entities.network.BaseResponse;

/**
 * The registration response entity.
 */
public class RegisterResponse extends BaseResponse implements Serializable {
    private Boolean result;
    public Boolean getResult() {
        return result;
    }
}
