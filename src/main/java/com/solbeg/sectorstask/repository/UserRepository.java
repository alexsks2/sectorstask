package com.solbeg.sectorstask.repository;

import com.solbeg.sectorstask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByName(String name);
}
