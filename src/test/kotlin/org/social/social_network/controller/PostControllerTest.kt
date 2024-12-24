package org.social.social_network.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.anyOf
import org.mockito.ArgumentMatchers.any
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.social.social_network.container.PostgresContainer
import org.social.social_network.dto.PostRqDto
import org.social.social_network.entity.Post
import org.social.social_network.entity.User
import org.social.social_network.model.CommentNotification
import org.social.social_network.repository.PostRepository
import org.social.social_network.repository.UserRepository
import org.social.social_network.service.NotificationService
import org.social.social_network.stubs.stub
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
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
    @MockBean
    private lateinit var notificationService: NotificationService

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
    @AfterEach
    fun clean(){
        postRepository.deleteAll()
        userRepository.deleteAll()

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
        //Given
        val user1 = User().apply { name = "User1" }
        val user2 = User().apply { name = "User2" }
        userRepository.save(user1)
        userRepository.save(user2)
        val savedPostOne = postRepository.save(Post().stub(user1.id, "one"))
        val savedPostTwo = postRepository.save(Post().stub(user1.id, "two"))
        val savedPostThree = postRepository.save(Post().stub(user2.id, "three"))
        val savedPostFour = postRepository.save(Post().stub(user2.id, "four"))

        //When
        val json = mockMvc.get("$POST_PATH/user/${user1.id}") {
            characterEncoding = Charsets.UTF_8.name()
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }.andReturn().response.contentAsString

        val result = objectMapper.readValue(json, object : TypeReference<List<Post>>() {})

        //Then
        assertThat(result)
            .isNotNull
            .hasSize(2)

        assertThat(result)
            .isNotNull
            .filteredOn { it.id == savedPostOne.id }
            .first()
            .matches({ it.description == "one" }, "description")

        assertThat(result)
            .isNotNull
            .filteredOn { it.id == savedPostTwo.id }
            .first()
            .matches({ it.description == "two" }, "description")
    }
    @Test
    fun addPostTest(){
        //Given
        val user1 = User().apply { name = "User1" }
        userRepository.save(user1)
        val postRqDto = PostRqDto(
            user1.id,
            "Simple desc"
        )
        Mockito.doNothing().`when`(notificationService).sendNotification(any(CommentNotification::class.java))
         //When
         mockMvc.post(POST_PATH) {
            characterEncoding = Charsets.UTF_8.name()
             contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(postRqDto)
        }.andExpect {
            status { isCreated() }
        }
        //Then
        Mockito.verify(notificationService,times(1))
            .sendNotification(any(CommentNotification::class.java))
       val post = postRepository.findAll().first()
        assertThat(post)
            .isNotNull
            .matches({ it.description == "Simple desc" }, "description")
            .matches({it.authorId == user1.id},"authorId")
    }
}