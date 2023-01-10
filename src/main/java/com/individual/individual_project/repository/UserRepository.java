package com.individual.individual_project.repository;

import com.individual.individual_project.entity.User;
import com.individual.individual_project.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
