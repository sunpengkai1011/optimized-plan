package industryproject.mit.deliveryoptimise.entities;

import java.io.Serializable;

/**
 * The user information entity.
 */
public class UserInfo implements Serializable{
    private String id;
    private int role;
    private String role_name;
    private String user_name;
    private String password;
    private String phone;
    private String email;

    public UserInfo() {
    }

    public UserInfo(int role, String user_name, String password, String phone, String email) {
        this.role = role;
        this.user_name = user_name;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
