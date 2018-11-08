package industryproject.mit.deliveryoptimise.entities.network;

import java.io.Serializable;

/**
 * The base response entity
 */
public class BaseResponse implements Serializable{
    private int code;//The response code from backend server
    private String message;//The response message from backend server

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
