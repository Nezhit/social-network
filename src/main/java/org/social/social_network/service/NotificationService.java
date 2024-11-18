package org.social.social_network.service;

import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;
import org.social.social_network.model.CommentNotification;

public interface NotificationService {
   CommentNotification sentNotification(Post post, Comment comment);

}
