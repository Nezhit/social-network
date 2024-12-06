package org.social.social_network.stubs

import org.social.social_network.entity.User

fun User.stub() = User().apply {
    name = "user1"
}