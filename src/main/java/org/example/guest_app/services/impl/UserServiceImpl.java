package org.example.guest_app.services.impl;

import org.example.guest_app.entities.User;
import org.example.guest_app.repositories.UserRepository;
import org.example.guest_app.requests.UserCreateRequest;
import org.example.guest_app.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User saveUser(UserCreateRequest userCreateRequest) {
        return userRepository.save(toEntity(userCreateRequest));
    }




    @Override
    public User updateService(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundedUser = user.get();
            foundedUser.setUserName(newUser.getUserName());
            foundedUser.setPassword(newUser.getPassword());
            return userRepository.save(foundedUser);
        }
        else{
            //güncellenmesi gerekilen user'ın bulunamadığına dair bir customer exception eklenecek.
            return null;
        }
    }

    private User toEntity(UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setUserName(userCreateRequest.getUserName());
        user.setPassword(userCreateRequest.getPassword());
        return user;
    }
}
