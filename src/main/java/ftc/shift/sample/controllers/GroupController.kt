package ftc.shift.sample.controllers

import com.google.gson.Gson
import ftc.shift.sample.Utils
import ftc.shift.sample.models.Group
import ftc.shift.sample.models.Subscription
import ftc.shift.sample.models.User
import ftc.shift.sample.repositories.GroupRepository
import ftc.shift.sample.repositories.SubscribeRepository
import ftc.shift.sample.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/api/v001/groups")
class GroupController @Autowired constructor(private val groupRepository: GroupRepository,
                                             private val userRepository: UserRepository,
                                             private val subscribeRepository: SubscribeRepository) {
    private val gson = Gson()
    @PostMapping
    fun createGroup(@RequestParam name: String?, @RequestParam count_people: Int,
                    @RequestParam user_id: Long): ResponseEntity<String> {
        val group = Group(name = name, creator = userRepository.findById(user_id).get(), countPeople = count_people)
        val result = groupRepository.saveAndFlush(group)
        return ResponseEntity.ok(result.link)
    }

    @PostMapping("/without_user_id")
    fun createGroupWithoutUserId(@RequestParam name: String, @RequestParam count_people: Int,
                                 @RequestParam user_name: String): ResponseEntity<String> {
        val user = userRepository.saveAndFlush(User(user_name))
        val group = Group(name = name, creator = userRepository.findById(user.id).get(), countPeople = count_people)
        val result = groupRepository.saveAndFlush(group)
        result.URL = result.link
        return ResponseEntity.ok(gson.toJson(result))
    }

    @GetMapping("/get_qr_link/{group_id}")
    fun getQrCodeLink(@PathVariable(name = "group_id") groupId : Long) : ResponseEntity<String> {
        val link = groupRepository.findById(groupId).get().link

        return ResponseEntity.ok("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=$link")
    }

    @GetMapping("/get_qr_img/{group_id}")
    fun getQrCodeImage(@PathVariable(name = "group_id") groupId: Long) : RedirectView {
        val link = groupRepository.findById(groupId).get().link
        return RedirectView("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=$link")
    }

    @GetMapping("/{groupId}")
    fun readGroup(@PathVariable groupId: Long): ResponseEntity<String> {
        val group = groupRepository.findById(groupId).get()
        return ResponseEntity.ok(gson.toJson(group))
    }

    @GetMapping("/users/{group_id}")
    fun getSubscribes(@PathVariable("group_id") groupId: Long): ResponseEntity<String> {
        val subs = subscribeRepository.findByGroupId(groupId)
        val users = subs.map { it.user }
        return ResponseEntity.ok(gson.toJson(users))
    }

    @GetMapping
    fun listGroups(): ResponseEntity<String> {
        val groups: Collection<Group> = groupRepository.findAll()
        return ResponseEntity.ok(gson.toJson(groups))
    }

    @PatchMapping
    fun updateGroup(@RequestBody json: String?): ResponseEntity<String> {
        val updatedGroup = groupRepository.saveAndFlush(gson.fromJson(json, Group::class.java))
        return ResponseEntity.ok(gson.toJson(updatedGroup))
    }

    @DeleteMapping("/{groupId}")
    fun deleteGroup(@PathVariable groupId: Long): ResponseEntity<*> {
        groupRepository.deleteById(groupId)
        return ResponseEntity.ok().build<Any>()
    }

}