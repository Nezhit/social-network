package org.social.social_network.controller;

import lombok.RequiredArgsConstructor;
import org.social.api.PostsApi;
import org.social.model.PostRqDto;
import org.social.model.PostRsDto;
import org.social.model.PostUpdateRqDto;
import org.social.social_network.dto.PostRqModel;
import org.social.social_network.mapper.PostMapper;
import org.social.social_network.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PostController implements PostsApi {

    private final PostService postService;
    private final PostMapper postMapper;

    @Override
    public ResponseEntity<Void> addPost(PostRqDto postRqDto) {
        PostRqModel model = postMapper.postRqDtoToPostModel(postRqDto);
        postService.addPost(model);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<PostRsDto>> getAllPosts() {
        return PostsApi.super.getAllPosts();
    }

    @Override
    public ResponseEntity<List<PostRsDto>> getAllPostsByUser(UUID userId) {
        return PostsApi.super.getAllPostsByUser(userId);
    }

    @Override
    public ResponseEntity<List<PostRsDto>> getRecommendations(UUID userId) {
        return PostsApi.super.getRecommendations(userId);
    }

    @Override
    public ResponseEntity<PostRsDto> updatePost(UUID id, PostUpdateRqDto postUpdateRqDto) {
        return PostsApi.super.updatePost(id, postUpdateRqDto);
    }
}