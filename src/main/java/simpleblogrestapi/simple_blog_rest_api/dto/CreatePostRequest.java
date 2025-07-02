package simpleblogrestapi.simple_blog_rest_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import simpleblogrestapi.simple_blog_rest_api.model.User;

@Getter
public class CreatePostRequest {

    @NotBlank
    @Size(min = 3, max = 256, message = "Title minimal 3 karakter dan maksimal 256 karakter")
    private String title;

    @Size(min = 3, max = 2048, message = "Content minimal 3 karakter dan maksimal 256 karakter")
    private String content;

    @NotBlank
    private User user;

}
