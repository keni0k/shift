package ftc.shift.sample.controllers

import com.google.gson.Gson
import ftc.shift.sample.models.Subscription
import ftc.shift.sample.models.User
import ftc.shift.sample.repositories.GroupRepository
import ftc.shift.sample.repositories.SubscribeRepository
import ftc.shift.sample.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
                         @RequestParam(name = "user_id") userId: Long): String {
        val subscription = Subscription(userId, groupId)
        subscribeRepository.save(subscription)
        return String.format("redirect:/api/v001/groups/%d", groupId)
    }

    @GetMapping("/subscribe/{group_id}")
    fun subscribeToGroupWithoutUser(@PathVariable(name = "group_id") groupId: Long,
                                    @RequestParam(name = "user_name", required = false) userName: String?,
                                    @RequestParam(name = "user_likes", required = false) likes: String?,
                                    @RequestParam(name = "user_dislikes", required = false) dislikes: String?): ResponseEntity<String> {
        if (userName == null || likes == null || dislikes == null)
            return ResponseEntity.ok(gson.toJson(groupRepository.findById(groupId)))
        val user = userRepository.saveAndFlush(User(userName, likes, dislikes))
        val subscription = subscribeRepository.saveAndFlush(Subscription(user.id, groupId))
        return ResponseEntity.ok(gson.toJson(subscription))
    }
}