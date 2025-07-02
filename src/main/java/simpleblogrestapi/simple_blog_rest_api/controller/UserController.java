package simpleblogrestapi.simple_blog_rest_api.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import simpleblogrestapi.simple_blog_rest_api.dto.CreateUserRequest;
import simpleblogrestapi.simple_blog_rest_api.model.User;
import simpleblogrestapi.simple_blog_rest_api.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/api/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PostMapping(path = "/api/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> storeUser(@ModelAttribute @Valid CreateUserRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors().getFirst().getDefaultMessage());
        }

        userService.storeUser(request.getUsername(), request.getEmail());
        return ResponseEntity.ok().body("User successfully created");
    }

    @PutMapping(path = "/api/users/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable UUID id, @ModelAttribute @Valid CreateUserRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Your input is invalid");
        }

        userService.updateUser(id, request.getUsername(), request.getEmail());
        return ResponseEntity.ok().body("User successfully updated");
    }

    @DeleteMapping("/api/users/{id}/delete")
    public ResponseEntity<String> deleteUser(@PathVariable(required = true) UUID id) {
        User user = userService.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body("User is not found");
        }

        userService.deleteUser(id);
        return ResponseEntity.ok().body("User successfully deleted");
    }

}
