package org.social.social_network.service.impl;

import org.social.social_network.model.CommentNotification;
import org.social.social_network.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(CommentNotification notification) {
        final String msg = String.format("Пользователь с id = %s создал новый пост c id = %s \\n дата публикации: %s",
                notification.authorId(), notification.postId(), notification.pubDate());
        System.out.println(msg);
    }
}
