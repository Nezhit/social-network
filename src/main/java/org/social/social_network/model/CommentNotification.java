package org.social.social_network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.social.social_network.entity.Comment;
import org.social.social_network.entity.Post;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentNotification {
    private Post post;
    private Comment comment;



}
