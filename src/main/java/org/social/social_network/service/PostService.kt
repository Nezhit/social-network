package org.social.social_network.service

import org.social.social_network.dto.PostRqDto
import org.social.social_network.dto.PostUpdateRqDto
import org.social.social_network.entity.Comment
import org.social.social_network.entity.Post
import org.social.social_network.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

interface PostService {
    fun findAll(): List<Post>

    fun findAll(userId: UUID): List<Post>

    fun findRecommendations(userId: UUID): List<Post>

    fun addPost(postRqDto: PostRqDto)

    fun updatePost(id: UUID, postUpdateRqDto: PostUpdateRqDto): Post
}
@Service
open class PostServiceImpl(
    private val postRepository: PostRepository
) : PostService {

    @Transactional
    override fun findAll(): List<Post> {
        return postRepository.findAll()
    }

    @Transactional
    override fun findAll(userId: UUID): List<Post> {
        return postRepository.findAllByAuthorId(userId)
    }

    @Transactional
    override fun findRecommendations(userId: UUID): List<Post> {
        return postRepository.findAllByAuthorId(userId)
            .flatMap { post -> post.comments!! }
            .map(Comment::authorId)
            .flatMap { commentAuthorId ->
                commentAuthorId?.let { findAll(it).take(5) } ?: emptyList()
            }
    }

    @Transactional
    override fun addPost(postRqDto: PostRqDto) {
        val newPost = Post(
            authorId = postRqDto.authorId,
            description = postRqDto.description
        )
        postRepository.save(newPost)
    }

    @Transactional
    override fun updatePost(id: UUID, rqDto: PostUpdateRqDto): Post {
        val post = postRepository.findById(id)
            .orElseThrow()

        val newPost = Post(
            id = post.id,
            pubDate = post.pubDate,
            authorId = post.authorId,
            likes = rqDto.likes,
            description = rqDto.description,
            picture = post.picture,
            comments = post.comments
        )

        return postRepository.save(newPost)
    }
}
