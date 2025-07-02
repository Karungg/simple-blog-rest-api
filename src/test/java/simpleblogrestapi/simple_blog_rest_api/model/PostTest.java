package simpleblogrestapi.simple_blog_rest_api.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostTest {

    @Test
    void createPost() {
        User user = new User();
        user.setUsername("user 1");
        user.setEmail("user1@gmail.com");

        Post post = new Post();
        post.setTitle("post 1");
        post.setContent("content post 1");
        post.setUser(user);

        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post.getTitle()).isEqualTo("post 1");
        Assertions.assertThat(post.getUser().getUsername()).isEqualTo("user 1");
    }

    @Test
    void createManyPost() {
        User user = new User();
        user.setUsername("user 1");
        user.setEmail("user1@gmail.com");

        Post post = new Post();
        post.setTitle("post 1");
        post.setContent("content post 1");
        post.setUser(user);

        Post post2 = new Post();
        post2.setTitle("post 2");
        post2.setContent("content post 2");
        post2.setUser(user);

        Assertions.assertThat(post).isNotNull();
        Assertions.assertThat(post2).isNotNull();
        Assertions.assertThat(post.getUser()).isEqualTo(post2.getUser());
    }

}
