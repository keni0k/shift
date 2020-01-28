package ftc.shift.sample.models;

import ftc.shift.sample.Utils;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    Integer countPeople;

    public Group(){}

    public Group(String name, User creator, Integer countPeople) {
        this.name = name;
        this.creator = creator;
        this.countPeople = countPeople;
    }
    public String getLink(){
        return Utils.getLinkById(id);
    }
}
