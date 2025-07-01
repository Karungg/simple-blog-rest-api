package simpleblogrestapi.simple_blog_rest_api.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Test
    void createUser() {
        User user = new User();
        user.setUsername("user 1");
        user.setEmail("user1@gmail.com");

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo("user 1");
        Assertions.assertThat(user.getEmail()).isEqualTo("user1@gmail.com");
    }
}