package simpleblogrestapi.simple_blog_rest_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {

    @Size(min = 3, max = 256, message = "Username minimal 3 karakter dan maksimal 256 karakter")
    @NotBlank
    private String username;

    @Size(min = 10, max = 256, message = "Email minimal 10 karakter dan maksimal 256 karakter")
    @Email
    @NotBlank
    private String email;

}
