package com.aimaginarium.repository;

import com.aimaginarium.model.UserGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserGalleryRepository extends JpaRepository<UserGallery, Long> {

    Optional<UserGallery> findUserGalleryByUserId(Long userId);

}
