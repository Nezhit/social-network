package org.social.social_network.repository;

import org.social.social_network.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PictureRepository extends JpaRepository<Picture, UUID> {
}
