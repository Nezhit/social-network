package org.social.social_network.controller;

import lombok.RequiredArgsConstructor;
import org.social.api.SpringBootGenerationForPostEntityApi;
import org.social.social_network.dto.PostUpdateRqDto;
import org.social.social_network.dto.PostRqDto;
import org.social.social_network.entity.Post;
import org.social.social_network.mapper.PostMapper;
import org.social.social_network.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController implements SpringBootGenerationForPostEntityApi {

    private final PostService postService;
    private final PostMapper postMapper;

    @Override
    public ResponseEntity<List<Post>> apiPostAllGet() {
        return ResponseEntity.ok(postService.findAll());
    }

    @Override
    public ResponseEntity<List<Post>> apiPostUserUserIdGet(UUID userId) {
        return ResponseEntity.ok(postService.findAll(userId));
    }

    @Override
    public ResponseEntity<List<Post>> apiPostRecommendationsUserIdGet(UUID userId) {
        return ResponseEntity.ok(postService.findRecommendations(userId));
    }



    @Override
    public ResponseEntity<Post> apiPostIdPut(UUID id, PostUpdateRqDto postUpdateRqDto) {
        Post post = postService.updatePost(id, postUpdateRqDto);
        return ResponseEntity.ok(post);
    }

    @Override
    public ResponseEntity<Void> apiPostPost(PostRqDto postRqDto) {
        postService.addPost(postRqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}