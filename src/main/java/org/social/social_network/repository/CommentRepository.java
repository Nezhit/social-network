package org.social.social_network.repository;

import org.social.social_network.entity.Comment;
import org.springframework.data.repository.ListCrudRepository;

import java.util.UUID;

public interface CommentRepository extends ListCrudRepository<Comment, UUID> {

}