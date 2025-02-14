package org.social.social_network.service;

import org.social.social_network.dto.PostRqModel;
import org.social.social_network.dto.PostUpdateRqModel;
import org.social.social_network.entity.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> findAll();

    List<Post> findAll(UUID userId);

    List<Post> findRecommendations(UUID userId);

    void addPost(PostRqModel postRqModel);

    Post updatePost(UUID id, PostUpdateRqModel postUpdateRqModel);
}