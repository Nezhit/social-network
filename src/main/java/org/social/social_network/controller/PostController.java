package org.social.social_network.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.social.api.PostsApi;
import org.social.model.PostRqDto;
import org.social.model.PostRsDto;
import org.social.model.PostUpdateRqDto;
import org.social.social_network.dto.PostRqModel;
import org.social.social_network.entity.Post;
import org.social.social_network.mapper.PostMapper;
import org.social.social_network.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
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
        List<PostRsDto> responseList = postService.findAll().stream()
                .map(postMapper::postToPostRsDto)
                .toList();
        log.info("Выполнен запрос getAllPosts вернул {}",responseList);
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<List<PostRsDto>> getAllPostsByUser(UUID userId) {
        List<PostRsDto> responseList = postService.findAll(userId).stream()
                .map(postMapper::postToPostRsDto)
                .toList();
        log.info("Выполнен запрос getAllPostsByUser вернул {}",responseList);
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<List<PostRsDto>> getRecommendations(UUID userId) {
        List<PostRsDto> responseList = postService.findRecommendations(userId).stream()
                .map(postMapper::postToPostRsDto)
                .toList();;
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<PostRsDto> updatePost(UUID id, PostUpdateRqDto postUpdateRqDto) {
        PostRsDto response = postMapper.postToPostRsDto(postService.updatePost(
                id,
                postMapper.postUpdateRqDtoToPostUpdateModel(postUpdateRqDto)
        ));
        return ResponseEntity.ok(response);
    }
}