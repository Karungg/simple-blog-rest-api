package simpleblogrestapi.simple_blog_rest_api.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import simpleblogrestapi.simple_blog_rest_api.model.User;
import simpleblogrestapi.simple_blog_rest_api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void storeUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UUID id, String username, String email) {
        User user = userRepository.findById(id).orElse(null);
        user.setUsername(username);
        user.setEmail(email);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
