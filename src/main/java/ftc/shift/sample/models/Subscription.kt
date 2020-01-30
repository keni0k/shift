package ftc.shift.sample.models

import javax.persistence.*


@Entity
data class Subscription(@ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id")
                        val user: User? = null,
                        @ManyToOne @JoinColumn(name = "group_id", referencedColumnName = "id")
                        val group: Group? = null,
                        @OneToOne @JoinColumn(name = "target_id", referencedColumnName = "id")
                        var target: User? = null,
                        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                        val id: Long = 0)