package org.social.social_network.service.impl;

import lombok.RequiredArgsConstructor;
import org.social.social_network.dto.PostRqModel;
import org.social.social_network.dto.PostUpdateRqModel;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.model.CommentNotification;
import org.social.social_network.repository.PostRepository;
import org.social.social_network.service.NotificationService;
import org.social.social_network.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final NotificationService notificationService;

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
    public void addPost(PostRqModel postRqModel) {
        Post newPost = new Post();
        newPost.setAuthorId(postRqModel.authorId());
        newPost.setDescription(postRqModel.description());
        Post savedPost = postRepository.save(newPost);
        CommentNotification commentNotification = new CommentNotification(postRqModel.authorId(), savedPost.getAuthorId(), Instant.now());
        notificationService.sendNotification(commentNotification);
    }

    @Override
    @Transactional
    public Post updatePost(UUID id, PostUpdateRqModel rqDto) {
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