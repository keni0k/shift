package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

}
