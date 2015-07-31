package app.smartBilling.com.model;

/**
 * Created by vikas on 31/07/15.
 */
public class User {

    private String uuid;
    private String name;
    private String phoneNumber;
    private String email;

    private User(String name, String phoneNumber, String email){

    }
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
