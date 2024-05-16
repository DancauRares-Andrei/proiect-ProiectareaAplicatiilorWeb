package ro.brainybattle.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.brainybattle.api.service.*;
import ro.brainybattle.model.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class APIController {
    @Autowired
    UACService uacService;
    @Autowired
    ChatService chatService;
    @Autowired
    LectureService lectureService;
    @Autowired
    UserGameStatService userGameStatService;
    //Creare utilizator nou.
    @PostMapping(path="/uac/new-user")
    public ResponseEntity<Map<String,String>> createUser(@RequestBody User new_user){
        return uacService.createUser(new_user);
    }
    //Autentificare utilizator existent.
    @PostMapping("/uac/login")
    public Map<String,String> loginUser(@RequestBody UserDTO userlogin) {
        return uacService.loginUser(userlogin);
    }
    //Deconectare utilizator existent.
    @PostMapping("/uac/logout")
    public ResponseEntity<Map<String,String>> logout(@RequestHeader("Authorization") String token) {
        return uacService.logout(token);
    }
    //Administratorul poate vedea datele utilizatorilor.
    @GetMapping(path = "/uac/accounts")
    public ResponseEntity<List<User>> getUsers() {
        return uacService.getUsers();
    }
    //Administratorul poate vedea datele unui utilizator.
    @GetMapping(path = "/uac/accounts/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return uacService.getUser(id);
    }
    //Utilizatorul poate sterge un utilizator cu un id.
    @DeleteMapping(path = "/uac/accounts/{id}")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable("id") long id){
        return uacService.deleteUser(id);
    }
    //Utilizatorul isi poate actualiza parola.
    @PutMapping(path = "/uac/accounts/{username}")
    public ResponseEntity<Map<String,String>> updateUser(@PathVariable("username") String username,@RequestBody String new_pass){
        return uacService.updateUser(username,new_pass);
    }
    //Adaugarea unui nou mesaj.
    @PostMapping("/chat/new-message")
    public ResponseEntity<Map<String,String>> postMessage(@RequestBody String content){
        return chatService.postMessage(content);
    }
    //Primirea paginilor cu 20 de mesaje, implicit prima
    @GetMapping("/chat")
    public List<MessageDTO> getMessages(@RequestParam(name = "page", defaultValue = "1") int page){
        return chatService.getMessages(page);
    }
    //Stergerea unui mesaj
    @DeleteMapping("/chat/{id_mesaj}")
    public ResponseEntity<Map<String,String>> deleteMessage(@PathVariable long id_mesaj){
        return chatService.deleteMessage(id_mesaj);
    }
    //Vizualizarea mesajelor pentru admin pentru a identifica id-ul mesajului de sters.
    @GetMapping("/chat/admin")
    public ResponseEntity<List<Message>> viewMessageAdmin(){
        return chatService.viewMessageAdmin();
    }

    //Vizualizarea cursului pentru o epoca
    @GetMapping("/lecture/{epoch_name}")
    public ResponseEntity<Lecture> getLecture(@PathVariable String epoch_name){
        return lectureService.getLecture(epoch_name);
    }
    @GetMapping("/details")
    public ResponseEntity<UserGameStatDTO> getUserDetails(){
        return userGameStatService.getUserDetails();
    }
    @PutMapping("/details/new-score")
    public ResponseEntity<String> updateUserScore(@RequestBody Integer new_score){
        return userGameStatService.updateUserScore(new_score);
    }
    @PutMapping("/details/new-epoch")
    public ResponseEntity<String> updateUserEpoch(@RequestBody String new_epoch){
        return userGameStatService.updateUserEpoch(new_epoch);
    }
}
