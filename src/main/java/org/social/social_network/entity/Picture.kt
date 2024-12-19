package org.social.social_network.entity

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Data
@Table(name = "pictures")
class Picture {
    @Id
     val id:UUID ?= null;
     val content: ByteArray ?=null
     val created : Instant ?= null
     val postId: UUID ?=null

}