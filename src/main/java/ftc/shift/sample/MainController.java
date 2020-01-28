package ftc.shift.sample;

import com.google.gson.Gson;
import ftc.shift.sample.models.Group;
import ftc.shift.sample.models.Subscription;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.GroupRepository;
import ftc.shift.sample.repositories.SubscribeRepository;
import ftc.shift.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private SubscribeRepository subscribeRepository;
    private Gson gson = new Gson();

    @Autowired
    public MainController(GroupRepository groupRepository,
                          UserRepository userRepository,
                          SubscribeRepository subscribeRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.subscribeRepository = subscribeRepository;
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/subscribe")
    public String subscribeToGroup(@RequestParam(name = "group_id") long groupId,
                                   @RequestParam(name = "user_id", required = false) Long userId) {
        if (userId != null) {
            Subscription subscription = new Subscription(userId, groupId);
            subscribeRepository.save(subscription);
            return String.format("redirect:/api/v001/groups/%d", groupId);
        }
        return String.format("redirect:/api/v001/users/add?group_id=%d", groupId);
    }

    @GetMapping("/add")
    public String createUser(@RequestParam Long group_id) {
        return "add";
    }

}
