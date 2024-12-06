package org.social.social_network.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.social.social_network.container.PostgresContainer
import org.social.social_network.entity.Post
import org.social.social_network.entity.User
import org.social.social_network.repository.PostRepository
import org.social.social_network.repository.UserRepository
import org.social.social_network.stubs.stub
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

private const val POST_PATH = "/api/post"

@Testcontainers
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class PostControllerTest {

    private val postgres = PostgresContainer.instance

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var context: WebApplicationContext

    @Autowired
    private lateinit var postRepository: PostRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeAll
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.log())
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun getAllPosts() {
        val savedUser = userRepository.save(User().stub())
        val savedPostOne = postRepository.save(Post().stub(savedUser.id, "one"))
        val savedPostTwo = postRepository.save(Post().stub(savedUser.id, "two"))

        val json = mockMvc.get("$POST_PATH/all") {
            characterEncoding = Charsets.UTF_8.name()
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }.andReturn().response.contentAsString

        val result = objectMapper.readValue(json, object : TypeReference<List<Post>>() {})

        assertThat(result)
            .isNotNull
            .matches({ it.size == 2 }, "size")
    }

    @Test
    fun getAllPostsByUser() {
        //TODO необходимо создать 3 поста у двух юзеров, в тесте дернуть того у кого 2 поста
    }
}