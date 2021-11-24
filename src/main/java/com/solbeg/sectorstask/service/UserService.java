package com.solbeg.sectorstask.service;

import com.solbeg.sectorstask.entity.User;
import com.solbeg.sectorstask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByName(user.getName());
        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            userToUpdate.setAgreeToTerms(user.isAgreeToTerms());
            userToUpdate.setSectorIds(user.getSectorIds());
            userRepository.save(userToUpdate);
        } else {
            userRepository.save(user);
        }
    }
}
