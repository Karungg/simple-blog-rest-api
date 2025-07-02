package simpleblogrestapi.simple_blog_rest_api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import simpleblogrestapi.simple_blog_rest_api.model.User;
import simpleblogrestapi.simple_blog_rest_api.repository.UserRepository;

import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void getUsers() throws Exception {
        mockMvc.perform(
                get("/api/users")).andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("[]")));
    }

    @Test
    void storeUser() throws Exception {
        mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "user 1")
                        .param("email", "user1@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("User successfully created")));

        List<User> user = userRepository.findAll();
        Assertions.assertThat(user.getFirst().getUsername()).isEqualTo("user 1");
    }

    @Test
    void updateUser() throws Exception {
        storeUser();
        List<User> user = userRepository.findAll();

        mockMvc.perform(
                put("/api/users/" + user.getFirst().getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username", "user 2")
                        .param("email", "user2@gmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("User successfully updated")));

        user = userRepository.findAll();
        Assertions.assertThat(user.getFirst().getUsername()).isEqualTo("user 2");
    }

    @Test
    void deleteUser() throws Exception {
        storeUser();
        List<User> user = userRepository.findAll();

        mockMvc.perform(
                delete("/api/users/" + user.getFirst().getId() + "/delete"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("User successfully deleted")));

        user = userRepository.findAll();
        Assertions.assertThat(user).isEmpty();
    }

}
