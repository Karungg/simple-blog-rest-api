package simpleblogrestapi.simple_blog_rest_api.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uniqueUsernameAndEmail", columnNames = {
        "username", "email" }))
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(min = 3, max = 256, message = "Username minimal 3 karakter dan maksimal 256 karakter")
    @NotBlank
    private String username;

    @Size(min = 10, max = 256, message = "Email minimal 10 karakter dan maksimal 256 karakter")
    @Email
    @NotBlank
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
