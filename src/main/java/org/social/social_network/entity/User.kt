package org.social.social_network.entity

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Data
@Table(name = "users")
class User {
    @Id
     val id: UUID? = null

     val name: String? = null

    @MappedCollection(idColumn = "author_id")
     val posts: Post? = null

    @MappedCollection(idColumn = "author_id")
     val comments: Set<Comment>? = null
}