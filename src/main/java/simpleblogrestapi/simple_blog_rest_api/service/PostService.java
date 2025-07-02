package simpleblogrestapi.simple_blog_rest_api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import simpleblogrestapi.simple_blog_rest_api.model.Post;
import simpleblogrestapi.simple_blog_rest_api.model.User;
import simpleblogrestapi.simple_blog_rest_api.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id);
    }

    public void storePost(String title, String content, User user) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUser(user);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(UUID id, String title, String content) {
        Post post = postRepository.findById(id).orElse(null);
        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(UUID id) {
        postRepository.deleteById(id);
    }
}
