package ftc.shift.sample.api;

import com.google.gson.Gson;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v001/users")
public class UserController {

    private UserRepository repository;
    private Gson gson = new Gson();
    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String json) {
        User result = repository.saveAndFlush(gson.fromJson(json, User.class));
        return ResponseEntity.ok(gson.toJson(result));
    }

    @GetMapping("/add")
    public ResponseEntity<String> readUser(@PathVariable Long userId) {
        User user = repository.findById(userId).get();
        return ResponseEntity.ok(gson.toJson(user));
    }

    @GetMapping
    public ResponseEntity<String> listUsers() {
        List<User> users = repository.findAll();
        return ResponseEntity.ok(gson.toJson(users));
    }

    @PatchMapping
    public ResponseEntity<String> updateUser(@RequestBody String json) {
        User updatedUser = repository.saveAndFlush(gson.fromJson(json, User.class));
        return ResponseEntity.ok(gson.toJson(updatedUser));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        repository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
