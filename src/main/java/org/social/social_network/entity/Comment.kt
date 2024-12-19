package org.social.social_network.entity

import lombok.Data
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Data
@Table(name = "comments")
class Comment {
    @Id
     val id: UUID? = null

     val text: String? = null

    @CreatedDate
     val pubDate: Instant = Instant.now()

    @LastModifiedDate
     val modifiedDate: Instant = Instant.now()

    val authorId: UUID? = null

     val postId: UUID? = null
}