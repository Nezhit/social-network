package org.social.social_network.model;

import java.time.Instant;
import java.util.UUID;

public record CommentNotification(UUID authorId, UUID postId, Instant pubDate) {

}
