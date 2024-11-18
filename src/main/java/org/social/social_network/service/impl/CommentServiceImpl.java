package org.social.social_network.service.impl;

import lombok.RequiredArgsConstructor;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.repository.CommentRepository;
import org.social.social_network.repository.PostRepository;
import org.social.social_network.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void addComment(UUID postId, UUID commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        post.addComment(comment);

    }
}
