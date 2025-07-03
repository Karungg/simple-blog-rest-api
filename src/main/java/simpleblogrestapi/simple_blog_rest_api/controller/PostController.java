package simpleblogrestapi.simple_blog_rest_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import simpleblogrestapi.simple_blog_rest_api.dto.CreatePostRequest;
import simpleblogrestapi.simple_blog_rest_api.model.Post;
import simpleblogrestapi.simple_blog_rest_api.service.PostService;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/api/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok().body(postService.findAllPosts());
    }

    @PostMapping(path = "/api/posts", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> storePost(@ModelAttribute @Valid CreatePostRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().toString());
        }

        postService.storePost(request.getTitle(), request.getContent(), request.getUserId());
        return ResponseEntity.ok().body("Post successfully created");
    }

    @PutMapping(path = "/api/posts/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @ModelAttribute @Valid CreatePostRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Your input is invalid");
        }

        postService.updatePost(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok().body("Post successfully updated");
    }

    @DeleteMapping("/api/posts/{id}/delete")
    public ResponseEntity<String> deletePost(@PathVariable(required = true) UUID id) {
        Post post = postService.findById(id).orElse(null);
        if (post == null) {
            return ResponseEntity.badRequest().body("Post is not found");
        }

        postService.deletePost(id);
        return ResponseEntity.ok().body("Post successfully deleted");
    }

}
