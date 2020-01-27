package ftc.shift.sample.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "group")
public class Group {
    @Id
    long id;


}
