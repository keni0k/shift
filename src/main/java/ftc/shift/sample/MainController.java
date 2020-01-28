package ftc.shift.sample;

import com.google.gson.Gson;
import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

    private GroupRepository repository;
    private Gson gson = new Gson();

    @Autowired
    public MainController(GroupRepository repository) {
        this.repository = repository;
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/subscribe/{id}")
    @ResponseBody
    public ResponseEntity<String> subscribeToGroup(@PathVariable long id) {
        return ResponseEntity.ok(gson.toJson(repository.findById(id).get()));
    }

}
