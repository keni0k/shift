package ftc.shift.sample.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "my_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    public User(String name, String likes, String dislikes) {
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
    }
    public User(){

    }
    public User(long id, String name, String likes, String dislikes){
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
        this.id = id;
    }

    private String name;
    private String likes;
    private String dislikes;
}
