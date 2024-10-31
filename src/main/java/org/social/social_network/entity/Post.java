package org.social.social_network.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Picture> pictures;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private int likes;

    Instant date;

}
