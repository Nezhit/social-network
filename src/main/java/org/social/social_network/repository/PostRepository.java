package org.social.social_network.repository;

import org.social.social_network.entity.Post;
import org.social.social_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByAuthor(User author);
}
