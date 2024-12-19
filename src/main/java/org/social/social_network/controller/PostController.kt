package org.social.social_network.controller

import lombok.RequiredArgsConstructor
import org.social.social_network.dto.PostRqDto
import org.social.social_network.dto.PostUpdateRqDto
import org.social.social_network.entity.Post
import org.social.social_network.service.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
class PostController(
    private val postService: PostService
) {

    @GetMapping("/all")
    fun getAllPosts(): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.findAll())
    }

    @GetMapping("/user/{userId}")
    fun getAllPostsByUser(@PathVariable userId: UUID): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.findAll(userId))
    }

    @GetMapping("/recommendations/{userId}")
    fun getRecommendations(@PathVariable userId: UUID): ResponseEntity<List<Post>> {
        return ResponseEntity.ok(postService.findRecommendations(userId))
    }

    @PostMapping
    fun addPost(@RequestBody postRqDto: PostRqDto): ResponseEntity<Void> {
        postService.addPost(postRqDto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable("id") id: UUID, @RequestBody postUpdateRqDto: PostUpdateRqDto): ResponseEntity<Post> {
        val post = postService.updatePost(id, postUpdateRqDto)
        return ResponseEntity.ok(post)
    }
}
