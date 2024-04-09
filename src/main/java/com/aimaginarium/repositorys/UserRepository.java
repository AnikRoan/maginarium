package com.aimaginarium.repositorys;

import com.aimaginarium.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
