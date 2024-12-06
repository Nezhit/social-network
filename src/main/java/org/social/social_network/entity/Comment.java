package org.social.social_network.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Data
@Table(name = "comments")
public class Comment {

    @Id
    private UUID id;

    private String text;

    @CreatedDate
    private Instant pubDate = Instant.now();

    @LastModifiedDate
    private Instant modifiedDate = Instant.now();

    private UUID authorId;

    private UUID postId;
}
