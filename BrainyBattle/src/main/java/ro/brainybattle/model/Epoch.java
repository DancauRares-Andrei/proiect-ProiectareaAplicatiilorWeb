package ro.brainybattle.model;

import jakarta.persistence.*;


@Entity
public class Epoch {
    @Id
    private String epoch_name;
    public String getName(){
        return this.epoch_name;
    }
    public void setName(String name1){
        this.epoch_name=name1;
    }
}
