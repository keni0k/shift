package ftc.shift.sample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("group_id")
    Group group;

    public Subscription(User user, Group group){
        this.user = user;
        this.group = group;
    }
}
