package ftc.shift.sample.controllers

import com.google.gson.Gson
import ftc.shift.sample.models.Subscription
import ftc.shift.sample.repositories.GroupRepository
import ftc.shift.sample.repositories.SubscribeRepository
import ftc.shift.sample.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController @Autowired constructor(private val groupRepository: GroupRepository,
                                            private val userRepository: UserRepository,
                                            private val subscribeRepository: SubscribeRepository) {
    private val gson = Gson()
    @GetMapping("/", "/add", "/subscribe")
    fun index(): String {
        return "index"
    }

    @GetMapping("/subscribe")
    fun subscribeToGroup(@RequestParam(name = "group_id") groupId: Long,
                         @RequestParam(name = "user_id", required = false) userId: Long?): String {
        if (userId != null) {
            val subscription = Subscription(userId, groupId)
            subscribeRepository.save(subscription)
            return String.format("redirect:/api/v001/groups/%d", groupId)
        }
        return String.format("redirect:/api/v001/users/add?group_id=%d", groupId)
    }
}