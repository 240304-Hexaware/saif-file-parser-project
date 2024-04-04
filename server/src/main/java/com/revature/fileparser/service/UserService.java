package com.revature.fileparser.service;

import java.util.List;

import com.revature.fileparser.entity.User;
import com.revature.fileparser.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User persistUser(User user){
        if(user.getUsername().isBlank() || user.getPassword().length() < 4) {
            return null;
        }
        return userRepository.save(user);
    }

    public User loginUser(User user) {
        User loggedinUser = getUserByUsername(user.getUsername());
        if(loggedinUser != null && loggedinUser.getPassword().equals(user.getPassword())){
            return loggedinUser;
        }
        return null;
    }
}
