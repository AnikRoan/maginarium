package com.aimaginarium.repository;

import com.aimaginarium.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
