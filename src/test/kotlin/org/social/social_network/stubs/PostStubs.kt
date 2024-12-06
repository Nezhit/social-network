package org.social.social_network.stubs

import org.social.social_network.entity.Post
import java.util.UUID

fun Post.stub(userId: UUID, descr: String = "sunrise") = Post().apply {
    authorId = userId
    description = descr
}