package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;

public class BaseResponse implements Serializable{
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
