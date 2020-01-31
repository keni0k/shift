package ftc.shift.sample.repositories

import ftc.shift.sample.models.Subscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscribeRepository : JpaRepository<Subscription, Long> {
    fun findByGroupId(groupId: Long): List<Subscription>
}