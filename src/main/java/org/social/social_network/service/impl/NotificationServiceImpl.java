package org.social.social_network.service.impl;

import lombok.RequiredArgsConstructor;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.model.CommentNotification;
import org.social.social_network.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    @Override
    public CommentNotification sentNotification(Post post, Comment comment) {
        return new CommentNotification(post,comment);
    }
}
