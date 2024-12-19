package org.social.social_network.entity

import lombok.Data
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table(name = "posts")
data class Post(
    @Id
    var id: UUID? = null,
    var description: String? = null,
    var likes: Int = 0,
    @CreatedDate
    var pubDate: Instant = Instant.now(),
    @LastModifiedDate
    var modifiedDate: Instant = Instant.now(),
    var authorId: UUID? = null,
    @MappedCollection(idColumn = "post_id")
    var picture: Picture? = null,
    @MappedCollection(idColumn = "post_id")
    var comments: Set<Comment>? = null
)