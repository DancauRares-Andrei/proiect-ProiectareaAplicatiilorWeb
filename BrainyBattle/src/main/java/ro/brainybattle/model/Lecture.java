package ro.brainybattle.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    @Id
    private int id;
    @Getter
    private String questionContent;
    @Getter
    private String answerA;
    @Getter
    private String answerB;
    @Getter
    private String answerC;
    @Getter
    private String answerD;
    @Getter
    private String correctAnswer;
    @OneToOne
    @JoinColumn
    private Epoch epoch;
}
