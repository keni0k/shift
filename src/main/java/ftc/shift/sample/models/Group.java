package ftc.shift.sample.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "my_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;

    @ManyToOne(optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="my_user_id")
    User creator;

    String URL;
}
