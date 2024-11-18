package org.social.social_network.service;

import java.util.UUID;

public interface CommentService {
    void addComment(UUID postId, UUID commentId);
}
