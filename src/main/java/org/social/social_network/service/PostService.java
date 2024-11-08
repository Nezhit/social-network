package org.social.social_network.service;

import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.entity.User;

import java.util.List;
import java.util.Map;

public interface PostService {
    List<Post> findAll();

    List<Post> findAll(User user);
    Map<Post,List<Comment>> findCommentsForEachPost();
    Map<User,List<Post>> findFirstFivePostsForEachCommentator();

    List<Post> findRecommendations(User user);
}