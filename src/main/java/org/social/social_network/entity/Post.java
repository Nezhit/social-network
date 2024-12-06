package org.social.social_network.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
@Table(name = "posts")
public class Post {

    @Id
    private UUID id;

    private String description;

    private int likes;

    @CreatedDate
    private Instant pubDate = Instant.now();

    @LastModifiedDate
    private Instant modifiedDate = Instant.now();

    private UUID authorId;

    @MappedCollection(idColumn = "post_id")
    private Picture picture;

    @MappedCollection(idColumn = "post_id")
    private Set<Comment> comments;
}
