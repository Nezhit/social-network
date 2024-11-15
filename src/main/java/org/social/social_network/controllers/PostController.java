package org.social.social_network.controllers;

import lombok.RequiredArgsConstructor;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.entity.User;
import org.social.social_network.repository.UserRepository;
import org.social.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

   private final UserRepository userRepository;

    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getAllPostsByUser(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return postService.findAll(user);
    }

//    @GetMapping("/comments")
//    public Map<Post, List<Comment>> getCommentsForEachPost() {
//        return postService.findCommentsForEachPost();
//    }
//
//    @GetMapping("/first-five-posts")
//    public Map<User, List<Post>> getFirstFivePostsForEachCommentator() {
//        return postService.findFirstFivePostsForEachCommentator();
//    }

    @GetMapping("/recommendations/{userId}")
    public List<Post> getRecommendations(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return postService.findRecommendations(user);
    }
}
