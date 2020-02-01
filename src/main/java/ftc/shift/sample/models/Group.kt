package ftc.shift.sample.models

import ftc.shift.sample.Utils
import javax.persistence.*
import kotlin.jvm.Transient

@Entity(name = "my_group")
data class Group(
                    @Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    val id: Long = 0,
                    @ManyToOne(optional = false, cascade = [CascadeType.ALL])
                    @JoinColumn(name = "my_user_id")
                    var creator: User? = null,
                    var name: String? = null,
                    var countPeople: Int? = null,
                    var finished: Boolean? = false,
                    @Transient var URL: String? = null
                ) {
    val link: String
        get() = Utils.getLinkById(id)!!
}