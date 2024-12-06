package org.social.social_network.repository;

import org.social.social_network.entity.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface UserRepository extends ListCrudRepository<User, UUID> {

}
