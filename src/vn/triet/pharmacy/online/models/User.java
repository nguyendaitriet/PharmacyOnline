package vn.triet.pharmacy.online.models;

public class User {
    private String id;
    private String fullName;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String email;
    private String userName;
    private String password;
    private Role role;

    public User() {}
    public User(String id, String fullName, String birthday, String phoneNumber, String address, String email, String userName, String password, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String raw) {
        String[] userInformation = raw.split(",");
        this.id = userInformation[0];
        this.fullName = userInformation[1];
        this.birthday = userInformation[2];
        this.phoneNumber = userInformation[3];
        this.address = userInformation[4];
        this.email = userInformation[5];
        this.userName = userInformation[6];
        this.password = userInformation[7];
        this.role = Role.fromValue(userInformation[8]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobile='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
