package org.social.social_network.controller;

import lombok.RequiredArgsConstructor;
import org.social.social_network.dto.PostRqDto;
import org.social.social_network.dto.PostUpdateRqDto;
import org.social.social_network.entity.Post;
import org.social.social_network.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getAllPostsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(postService.findAll(userId));
    }

    @GetMapping("/recommendations/{userId}")
    public ResponseEntity<List<Post>> getRecommendations(@PathVariable UUID userId) {
        return ResponseEntity.ok(postService.findRecommendations(userId));
    }

    @PostMapping
    public ResponseEntity<Void> addPost(@RequestBody PostRqDto postRqDto) {
        postService.addPost(postRqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") UUID id, @RequestBody PostUpdateRqDto postUpdateRqDto) {
        Post post = postService.updatePost(id, postUpdateRqDto);
        return ResponseEntity.ok(post);
    }
}