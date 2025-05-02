package Classes;

public class User {
    private String Email,Password,UserName,Phone,RenterId;

    public User(String email, String password, String userName, String phone,String RenterId) {
        Email = email;
        Password = password;
        UserName = userName;
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRenterId() {
        return RenterId;
    }

    public void setRenterId(String renterId) {
        RenterId = renterId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
