package ftc.shift.sample.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Subscription(val userId: Long? = null, val groupId: Long? = null,
                        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        val id: Long = 0)