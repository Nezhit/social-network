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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;


    private final UserRepository userRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findAll(User user) {
        return postRepository.findAll(user);
    }

//    @Override
//    public Map<Post, List<Comment>> findCommentsForEachPost() {
//        return commentRepository.findAll()
//                .stream()
//                .collect(Collectors.groupingBy(Comment::getPost));
//    }
//
//    @Override
//    public Map<User, List<Post>> findFirstFivePostsForEachCommentator() {
//        return commentRepository.findAll()
//                .stream()
//                .collect(Collectors.groupingBy(
//                        Comment::getAuthor,
//                        Collectors.collectingAndThen(
//                                Collectors.mapping(Comment::getPost, Collectors.toList()),
//                                list -> list.stream().limit(5).collect(Collectors.toList())
//                        )
//                ));
//    }

    @Override
    public List<Post> findRecommendations(User user) {
        return postRepository.findAll(user).stream()
                .flatMap(post -> post.getComments().stream())
                .map(Comment::getAuthor)
                .flatMap(commentAuthor -> findAll(commentAuthor).stream().limit(5))
                .toList();
    }
}
