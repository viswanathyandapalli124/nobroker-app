package com.chandu.NoBroker.service;

import com.chandu.NoBroker.model.User;
import com.chandu.NoBroker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
