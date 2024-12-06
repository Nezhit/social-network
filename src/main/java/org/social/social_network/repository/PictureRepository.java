package org.social.social_network.repository;

import org.social.social_network.entity.Picture;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface PictureRepository extends ListCrudRepository<Picture, UUID> {

}