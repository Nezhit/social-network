package org.social.social_network.service.impl;

import lombok.RequiredArgsConstructor;
import org.social.social_network.dto.PostRqDto;
import org.social.social_network.dto.PostUpdateRqDto;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.repository.PostRepository;
import org.social.social_network.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    @Transactional
    public List<Post> findAll(UUID userId) {
        return postRepository.findAllByAuthorId(userId);
    }

    @Override
    @Transactional
    public List<Post> findRecommendations(UUID userId) {
        return postRepository.findAllByAuthorId(userId).stream()
                .flatMap(post -> post.getComments().stream())
                .map(Comment::getAuthorId)
                .flatMap(commentAuthorId -> findAll(commentAuthorId)
                        .stream()
                        .limit(5))
                .toList();
    }

    @Override
    @Transactional
    public void addPost(PostRqDto postRqDto) {
        Post newPost = new Post();
        newPost.setAuthorId(postRqDto.authorId());
        newPost.setDescription(postRqDto.description());
        postRepository.save(newPost);
    }

    @Override
    @Transactional
    public Post updatePost(UUID id, PostUpdateRqDto rqDto) {
        final Post post = postRepository.findById(id)
                .stream()
                .findFirst()
                .orElseThrow();

        final Post newPost = new Post();
        newPost.setId(post.getId());
        newPost.setPubDate(post.getPubDate());
        newPost.setAuthorId(post.getAuthorId());
        newPost.setLikes(rqDto.likes());
        newPost.setDescription(rqDto.description());
        newPost.setPicture(post.getPicture());
        newPost.setComments(post.getComments());

        return postRepository.save(newPost);
    }
}