package ftc.shift.sample.models;

import javax.persistence.*;

public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn (name="my_user_id")
    User user;

    @ManyToOne(optional=false, cascade= CascadeType.ALL)
    @JoinColumn (name="my_group_id")
    Group group;
}
