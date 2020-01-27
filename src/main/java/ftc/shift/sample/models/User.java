package ftc.shift.sample.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "my_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String name;

}
