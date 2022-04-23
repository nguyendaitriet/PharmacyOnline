package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.User;

import java.util.List;

public interface ISignUpService {
    List<User> getUsers();

    User login(String username, String password);

    void add(User newUser);

    void update(User newUser);

    boolean isIdExisted(String id);

    boolean checkExistedEmail(String email);

    boolean checkExistedPhone(String phone);

    boolean checkExistedUserName(String userName);

    User getUserById(String id);
}
