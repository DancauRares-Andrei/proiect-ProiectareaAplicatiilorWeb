package ro.brainybattle.model;
import jakarta.persistence.*;
@Entity
public class Message {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long message_id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;
    public String getContent() {
        return content;
    }
    public Long getId() {
        return message_id;
    }
    public String getSenderUsername(){
        return user_id.getUsername();
    }
    public Message(){

    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setUser_id(User user){
        this.user_id=user;
    }
}
