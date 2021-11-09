package com.solbeg.sectorstask.service;

import com.solbeg.sectorstask.entity.User;
import com.solbeg.sectorstask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUser(String name) {
        return userRepository.getUserByName(name);
    }
}
