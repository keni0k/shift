package ftc.shift.sample.controllers

import com.google.gson.Gson
import ftc.shift.sample.models.User
import ftc.shift.sample.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v001/users")
class UserController @Autowired constructor(private val repository: UserRepository) {
    private val gson = Gson()
    @PostMapping
    fun createUser(@RequestBody json: String?): ResponseEntity<String> {
        val result = repository.saveAndFlush(gson.fromJson(json, User::class.java))
        return ResponseEntity.ok(gson.toJson(result))
    }

    @GetMapping("/{userId}")
    fun readUser(@PathVariable userId: Long): ResponseEntity<String> {
        val user = repository.findById(userId).get()
        return ResponseEntity.ok(gson.toJson(user))
    }

    @GetMapping
    fun listUsers(): ResponseEntity<String> {
        val users = repository.findAll()
        return ResponseEntity.ok(gson.toJson(users))
    }

    @PatchMapping
    fun updateUser(@RequestBody json: String?): ResponseEntity<String> {
        val updatedUser = repository.saveAndFlush(gson.fromJson(json, User::class.java))
        return ResponseEntity.ok(gson.toJson(updatedUser))
    }

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: Long): ResponseEntity<*> {
        repository.deleteById(userId)
        return ResponseEntity.ok().build<Any>()
    }

}