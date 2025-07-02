package simpleblogrestapi.simple_blog_rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simpleblogrestapi.simple_blog_rest_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
