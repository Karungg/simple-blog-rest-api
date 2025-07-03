package simpleblogrestapi.simple_blog_rest_api.controller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import simpleblogrestapi.simple_blog_rest_api.model.Post;
import simpleblogrestapi.simple_blog_rest_api.model.User;
import simpleblogrestapi.simple_blog_rest_api.repository.PostRepository;
import simpleblogrestapi.simple_blog_rest_api.repository.UserRepository;
import simpleblogrestapi.simple_blog_rest_api.service.UserService;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    private void createUser() {
        User user = new User();
        user.setUsername("user 1");
        user.setEmail("user1@gmail.com");
        userRepository.save(user);
    }

    @Test
    void getPosts() throws Exception {
        mockMvc.perform(
                get("/api/posts")).andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("[]")));
    }

    @Test
    void storePost() throws Exception {
        createUser();
        List<User> user = userService.findAllUsers();

        mockMvc.perform(
                post("/api/posts")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("title", "post 1")
                        .param("content", "content post 1")
                        .param("userId", user.getFirst().getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Post successfully created")));

        Assertions.assertThat(user.getFirst().getUsername()).isEqualTo("user 1");
    }

    @Test
    void updatePost() throws Exception {
        storePost();
        List<Post> posts = postRepository.findAll();
        List<User> user = userService.findAllUsers();

        mockMvc.perform(
                put("/api/posts/" + posts.getFirst().getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("title", "post 2")
                        .param("content", "content post 2")
                        .param("userId", user.getFirst().getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Post successfully updated")));

        posts = postRepository.findAll();
        Assertions.assertThat(posts.getFirst().getTitle()).isEqualTo("post 2");
    }

    @Test
    void deletePost() throws Exception {
        storePost();
        List<Post> posts = postRepository.findAll();

        mockMvc.perform(
                delete("/api/posts/" + posts.getFirst().getId() + "/delete"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Post successfully deleted")));

        posts = postRepository.findAll();
        Assertions.assertThat(posts).isEmpty();
    }

}
