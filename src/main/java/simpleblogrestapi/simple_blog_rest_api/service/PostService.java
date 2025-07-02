package simpleblogrestapi.simple_blog_rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simpleblogrestapi.simple_blog_rest_api.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
}
