package org.social.social_network.repository;

import org.social.social_network.entity.Post;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends ListCrudRepository<Post, UUID> {

    List<Post> findAllByAuthorId(UUID authorId);
}
