package com.revature.fileparser.controller;

import java.util.List;

import com.revature.fileparser.entity.User;
import com.revature.fileparser.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }


    @PostMapping(value= "/registration")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        User savedUser = userService.persistUser(user);
        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedUser);
    }

    @PostMapping(value= "/login")
    public ResponseEntity<User> loggedinUser(@RequestBody User user, HttpServletRequest request) {
        User loggedinUser = userService.loginUser(user);
        if(loggedinUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        HttpSession session = request.getSession();
        session.setAttribute("currentUser", loggedinUser.getUsername());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(loggedinUser);
    }
}
