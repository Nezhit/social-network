package org.social.social_network.service.impl;

import lombok.RequiredArgsConstructor;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.entity.User;
import org.social.social_network.repository.CommentRepository;
import org.social.social_network.repository.PostRepository;
import org.social.social_network.repository.UserRepository;
import org.social.social_network.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    private final UserRepository userRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAll(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return postRepository.findAll(user);
    }


    @Override
    public List<Post> findRecommendations(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return postRepository.findAll(user).stream()
                .flatMap(post -> post.getComments().stream())
                .map(Comment::getAuthor)
                .flatMap(commentAuthor -> findAll(commentAuthor.getId())
                        .stream()
                        .limit(5))
                .toList();
    }
}
