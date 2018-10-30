package industryproject.mit.deliveryoptimise.entities;

import java.io.Serializable;

import industryproject.mit.deliveryoptimise.entities.parcel.UAddress;

/**
 * The user information entity.
 */
public class UserInfo implements Serializable{
    private String userId;
    private String userName;
    private String password;
    private UAddress uAddress;

    public UserInfo() {
    }

    public UserInfo(String userName, String password, UAddress uAddress) {
        this.userName = userName;
        this.password = password;
        this.uAddress = uAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UAddress getuAddress() {
        return uAddress;
    }

    public void setuAddress(UAddress uAddress) {
        this.uAddress = uAddress;
    }
}
