package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.User;
import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class SignUpService implements ISignUpService {
    private static String path = "data/users.csv";

    @Override
    public List<User> getUsers() {
        List<User> newUsersList = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            newUsersList.add(new User(record));
        }
        return newUsersList;
    }

    @Override
    public User login(String username, String password) {
        return null;
    }

    @Override
    public void add(User newUser) {
        List<User> users = getUsers();
        users.add(newUser);
        CSVUtils.writeData(path, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId().equals(newUser.getId())) {
                if (newUser.getFullName() != null && !newUser.getFullName().isEmpty())
                    user.setFullName(newUser.getFullName());
                if (newUser.getPhoneNumber() != null && !newUser.getPhoneNumber().isEmpty())
                    user.setPhoneNumber(newUser.getPhoneNumber());
                if (newUser.getAddress() != null && !newUser.getAddress().isEmpty())
                    user.setAddress(newUser.getAddress());
                CSVUtils.writeData(path, users);
                break;
            }
        }
    }

    @Override
    public boolean isIdExisted(String id) {
        return getUserById(id) != null;
    }

    @Override
    public boolean checkExistedEmail(String email) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkExistedPhone(String phone) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getPhoneNumber().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkExistedUserName(String userName) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getUserName().equals(userName))
                return true;
        }

        return false;
    }

    @Override
    public User getUserById(String id) {
        List<User> users = getUsers();
        for (User user : users) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }
}
