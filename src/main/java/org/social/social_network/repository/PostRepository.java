package org.social.social_network.repository;

import org.social.social_network.entity.Post;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends ListCrudRepository<Post, UUID> {

    List<Post> findAllByAuthorId(UUID authorId);
}
