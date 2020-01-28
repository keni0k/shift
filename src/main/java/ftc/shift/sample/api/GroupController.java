package ftc.shift.sample.api;

import com.google.gson.Gson;
import ftc.shift.sample.models.Group;
import ftc.shift.sample.repositories.GroupRepository;
import ftc.shift.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v001/groups")
public class GroupController {

    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private Gson gson = new Gson();
    @Autowired
    public GroupController(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> createGroup(@RequestParam String name, @RequestParam int count_people,
                                              @RequestParam long user_id) {
        Group group = new Group(name, userRepository.findById(user_id).get(), count_people);
        Group result = groupRepository.saveAndFlush(group);
        return ResponseEntity.ok(result.getLink());
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<String> readGroup(@PathVariable Long groupId) {
        Group book = groupRepository.findById(groupId).get();
        return ResponseEntity.ok(gson.toJson(book));
    }

    @GetMapping
    public ResponseEntity<String> listGroups() {
        Collection<Group> books = groupRepository.findAll();
        return ResponseEntity.ok(gson.toJson(books));
    }

    @PatchMapping
    public ResponseEntity<String> updateGroup(@RequestBody String json) {
        Group updatedGroup = groupRepository.saveAndFlush(gson.fromJson(json, Group.class));
        return ResponseEntity.ok(gson.toJson(updatedGroup));
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId) {
        groupRepository.deleteById(groupId);
        return ResponseEntity.ok().build();
    }
}