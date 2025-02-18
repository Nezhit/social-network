package org.social.social_network.service;

import org.social.social_network.model.CommentNotification;

public interface NotificationService {
    void sendNotification(CommentNotification notification);

}
