package org.social.social_network.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private UUID id;

    private String text;

    private Instant published;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Post post;
}
