package org.social.social_network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.social.social_network.entity.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
