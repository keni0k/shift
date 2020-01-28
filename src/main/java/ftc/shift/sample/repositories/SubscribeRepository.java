package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Group;
import ftc.shift.sample.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscription, Long> {

}
