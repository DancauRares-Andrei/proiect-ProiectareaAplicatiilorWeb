package ro.brainybattle.model;

public class MessageDTO {
    private String content;
    private String username;
    public String getUsername(){
        return username;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content1){
        content=content1;
    }
    public void setUsername(String username1){
        username=username1;
    }
}
