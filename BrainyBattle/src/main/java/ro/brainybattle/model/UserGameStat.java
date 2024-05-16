package ro.brainybattle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserGameStat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_detail_id;
    @OneToOne
    @JoinColumn
    User user;
    Integer score;
    @ManyToOne
    @JoinColumn
    private Epoch epoch;
}
