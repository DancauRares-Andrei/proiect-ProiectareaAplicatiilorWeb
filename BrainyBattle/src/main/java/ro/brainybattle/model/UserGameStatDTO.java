package ro.brainybattle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserGameStatDTO {
    Integer score;
    String epoch_name;
    String username;
}
