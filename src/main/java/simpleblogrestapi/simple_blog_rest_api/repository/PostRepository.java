package simpleblogrestapi.simple_blog_rest_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simpleblogrestapi.simple_blog_rest_api.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

}
