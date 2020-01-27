package ftc.shift.sample.api;

import com.google.gson.Gson;
import ftc.shift.sample.models.Group;
import ftc.shift.sample.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v001/groups")
public class GroupController {

    private GroupRepository repository;
    private Gson gson = new Gson();
    @Autowired
    public GroupController(GroupRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group result = repository.saveAndFlush(group);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<String> readGroup(@PathVariable Long groupId) {
        Group book = repository.getOne(groupId);
        return ResponseEntity.ok(gson.toJson(book));
    }

    @GetMapping
    public ResponseEntity<String> listGroups() {
        Collection<Group> books = repository.findAll();
        return ResponseEntity.ok(gson.toJson(books));
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<Group> updateGroup(@RequestBody Group book) {
        Group updatedGroup = repository.saveAndFlush(book);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId) {
        repository.deleteById(groupId);
        return ResponseEntity.ok().build();
    }
}