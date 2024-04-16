package com.aimaginarium.repository;

import com.aimaginarium.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    @Query("SELECT ud FROM UserProfile ud WHERE ud.user.id = :userId")
    Optional<UserProfile> findUserProfileByUserId(@Param("userId") Long userId);
}
