package vn.triet.pharmacy.online.uml;

import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class User {
    private long id;
    private String fullName;
    private String birthday;
    private String phoneNumber;
    private String address;
    private String email;
    private String userName;
    private String password;
    private Role role;
    private long creationTime;


    public List<vn.triet.pharmacy.online.models.User> getUsers() {
        List<vn.triet.pharmacy.online.models.User> newUsersList = new ArrayList<>();
        List<String> records = CSVUtils.readData("h");
        for (String record : records) {
            newUsersList.add(new vn.triet.pharmacy.online.models.User(record));
        }
        return newUsersList;
    }


    public vn.triet.pharmacy.online.models.User login(String username, String password) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        for (vn.triet.pharmacy.online.models.User user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }


    public void add(vn.triet.pharmacy.online.models.User newUser) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        users.add(newUser);
        CSVUtils.writeData("h", users);
    }


    public void update(vn.triet.pharmacy.online.models.User newUser) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        for (vn.triet.pharmacy.online.models.User user : users) {
            if (user.getId() == newUser.getId()) {
                if (newUser.getFullName() != null && !newUser.getFullName().isEmpty())
                    user.setFullName(newUser.getFullName());
                if (newUser.getBirthday() != null && !newUser.getBirthday().isEmpty())
                    user.setBirthday(newUser.getBirthday());
                if (newUser.getPhoneNumber() != null && !newUser.getPhoneNumber().isEmpty())
                    user.setPhoneNumber(newUser.getPhoneNumber());
                if (newUser.getAddress() != null && !newUser.getAddress().isEmpty())
                    user.setAddress(newUser.getAddress());
                if (newUser.getEmail() != null && !newUser.getEmail().isEmpty())
                    user.setEmail(newUser.getEmail());
                if (newUser.getUserName() != null && !newUser.getUserName().isEmpty())
                    user.setUserName(newUser.getUserName());
                if (newUser.getPassword() != null && !newUser.getPassword().isEmpty())
                    user.setPassword(newUser.getPassword());
                CSVUtils.writeData("h", users);
                break;
            }
        }
    }


    public boolean isIdExisted(long id) {
        return getUserById(id) != null;
    }


    public boolean checkExistedEmail(String email) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        for (vn.triet.pharmacy.online.models.User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }


    public boolean checkExistedPhone(String phone) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        for (vn.triet.pharmacy.online.models.User user : users) {
            if (user.getPhoneNumber().equals(phone))
                return true;
        }
        return false;
    }


    public boolean checkExistedUserName(String userName) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        for (vn.triet.pharmacy.online.models.User user : users) {
            if (user.getUserName().equals(userName))
                return true;
        }
        return false;
    }


    public vn.triet.pharmacy.online.models.User getUserById(long id) {
        List<vn.triet.pharmacy.online.models.User> users = getUsers();
        for (vn.triet.pharmacy.online.models.User user : users) {
            if (user.getId() == id)
                return user;
        }
        return null;
    }


}
