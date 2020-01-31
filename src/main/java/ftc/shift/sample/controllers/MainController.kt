package ftc.shift.sample.controllers

import com.google.gson.Gson
import ftc.shift.sample.exception.BadRequestException
import ftc.shift.sample.exception.NotFoundException
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
import java.util.stream.IntStream
import kotlin.random.Random

@Controller
class MainController @Autowired constructor(private val groupRepository: GroupRepository,
                                            private val userRepository: UserRepository,
                                            private val subscribeRepository: SubscribeRepository) {
    private val gson = Gson()
    @GetMapping("/", "/add", "/subscribe", "/manage")
    fun index(): String {
        return "index"
    }

    @GetMapping("/subscribe/{group_id}")
    fun subscribeToGroup(@PathVariable(name = "group_id") groupId: Long,
                         @RequestParam(name = "user_id") userId: Long): ResponseEntity<String> {
        val group = groupRepository.findById(groupId).get()
        if (group.finished != null) {
            if (group.finished!!)
                throw BadRequestException("The group has already been formed")
        }
        val subscription = Subscription(userRepository.findById(userId).get(), group)
        subscribeRepository.saveAndFlush(subscription)
        return ResponseEntity.ok(gson.toJson(subscription))
    }

    @GetMapping("/subscription/{group_id}")
    fun subscribeWithoutUser(@PathVariable(name = "group_id") groupId: Long,
                            @RequestParam(name = "user_name", required = false) userName: String?,
                            @RequestParam(name = "user_likes", required = false) likes: String?,
                            @RequestParam(name = "user_dislikes", required = false) dislikes: String?): ResponseEntity<String> {
        if (userName == null || likes == null || dislikes == null)
            return ResponseEntity.ok(gson.toJson(groupRepository.findById(groupId)))
        val user = userRepository.saveAndFlush(User(userName, likes, dislikes))
        val subscription = subscribeRepository.saveAndFlush(Subscription(user=user, group=groupRepository.findById(groupId).get()))
        return ResponseEntity.ok(gson.toJson(subscription))
    }
    
    @GetMapping("/target/{user_id}")
    fun getUserTarget(@PathVariable(name = "user_id") userId: Long) : ResponseEntity<User?> {
        val target = subscribeRepository.findById(userId).get().target
        if (target != null) {
            return ResponseEntity.ok(target)
        } else {
            throw NotFoundException()
        }
    }

    @GetMapping("/finish/{group_id}")
    fun finishGroup(@PathVariable(name = "group_id") groupId: Long) : ResponseEntity<*> {
        val group = groupRepository.findById(groupId).get()
        if (group.finished != null) {
            if (group.finished!!)
                throw BadRequestException("The group has already been formed")
        }
        group.finished = true

        val subscriptions = subscribeRepository.findAll()
                .filter { s -> groupId == s.group?.id }
                .filterNotNull()
        val membersCount = subscriptions.size
        if (membersCount < 3) throw BadRequestException("Members count should be at least 3")

        val derangement = randomDerangement(membersCount)

        for(i in subscriptions.indices) {
            subscriptions[i].target = subscriptions[derangement[i]].user
            subscribeRepository.saveAndFlush(subscriptions[i])
        }

        groupRepository.saveAndFlush(group)
        return ResponseEntity.ok().build<Any>()
    }

    private fun randomPermutation(size: Int): IntArray {
        val array = IntStream.range(0, size).toArray()

        val random = Random
        for (i in size - 1 downTo 1) {
            val j = random.nextInt(i)

            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
        }
        return array
    }

    private fun randomDerangement(count: Int): IntArray {
        val array = randomPermutation(count)

        for (i in array.indices) {
            if (array[i] == i) return randomDerangement(count)
        }

        return array
    }
}