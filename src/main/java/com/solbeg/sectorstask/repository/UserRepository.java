package com.solbeg.sectorstask.repository;

import com.solbeg.sectorstask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByName(String name);
}
