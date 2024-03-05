package org.example.guest_app.services;

import org.example.guest_app.entities.User;
import org.example.guest_app.requests.UserCreateRequest;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(Long userId);

    void deleteUserById(Long userId);

    User saveUser(UserCreateRequest user);

    User updateService(Long userId, User newUser);
}
