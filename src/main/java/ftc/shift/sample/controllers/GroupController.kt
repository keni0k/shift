package ftc.shift.sample.controllers

import com.google.gson.Gson
import ftc.shift.sample.models.Group
import ftc.shift.sample.models.User
import ftc.shift.sample.repositories.GroupRepository
import ftc.shift.sample.repositories.SubscribeRepository
import ftc.shift.sample.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        return ResponseEntity.ok(result.link)
    }

    @GetMapping("/{groupId}")
    fun readGroup(@PathVariable groupId: Long): ResponseEntity<String> {
        val book = groupRepository.findById(groupId).get()
        return ResponseEntity.ok(gson.toJson(book))
    }

    /*@GetMapping("/subscribes/{group_id}")
    public ResponseEntity<String> subscribes(@PathVariable(name = "group_id") Long groupId) {
        subscribeRepository.findAllBy()
        return ResponseEntity.ok(gson.toJson(book));
    }*/
    @GetMapping
    fun listGroups(): ResponseEntity<String> {
        val books: Collection<Group> = groupRepository.findAll()
        return ResponseEntity.ok(gson.toJson(books))
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