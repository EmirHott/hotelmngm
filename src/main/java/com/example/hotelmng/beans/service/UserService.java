package com.example.hotelmng.beans.service;

import com.example.hotelmng.beans.model.User;
import com.example.hotelmng.beans.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void removeUser (User user){
        userRepository.delete(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User findById(Integer userId){
        return userRepository.getReferenceById(userId);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
