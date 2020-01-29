package ftc.shift.sample.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "my_user")
data class User(var name: String = "", var likes: String = "", var dislikes: String = "",
                @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id: Long = 0)