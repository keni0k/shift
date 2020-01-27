package ftc.shift.sample.api;

import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v001/users")
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User result = repository.saveAndFlush(user);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> readUser(@PathVariable Long userId) {
        User book = repository.getOne(userId);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = repository.findAll();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User book) {
        User updatedUser = repository.saveAndFlush(book);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        repository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
