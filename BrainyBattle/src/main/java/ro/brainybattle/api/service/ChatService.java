package ro.brainybattle.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ro.brainybattle.model.Message;
import ro.brainybattle.model.MessageDTO;
import ro.brainybattle.model.User;
import ro.brainybattle.repository.MessageRepository;
import ro.brainybattle.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(ChatService.class);
    public ResponseEntity<Map<String,String>> postMessage(String content){
        try{
            Message message=new Message();
            message.setContent(content);
            Optional<User> userOptional=userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            message.setUser_id(userOptional.get());
            messageRepository.save(message);
            Map<String, String> body = new HashMap<>();
            body.put("detail","Mesaj postat cu succes.");
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        }
        catch (Exception e){
            log.info("Nu s-a reusit adaugarea noului mesaj: "+e.getMessage());
            Map<String, String> body = new HashMap<>();
            body.put("detail",e.getMessage());
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }
    public List<MessageDTO> getMessages(int page){
        log.info("S-a cerut afisarea mesajelor din baza de date de catre utilizatorul " + SecurityContextHolder.getContext().getAuthentication().getName() + ".");
        int pageSize = 200;
        if (page > 1000) // O limitare pentru a nu cauta pagini inexistente
            page = 1000;
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("message_id").descending());
        Page<Message> messagePage = messageRepository.findMessages(pageable);

        List<MessageDTO> messageDTOs = messagePage.getContent().stream()
                .map(message -> {
                    MessageDTO messageDTO = new MessageDTO();
                    messageDTO.setContent(message.getContent());
                    messageDTO.setUsername(message.getSenderUsername());
                    return messageDTO;
                })
                .collect(Collectors.toList());
        Collections.reverse(messageDTOs);//Inversare pentru ca cele 200 de mesaje sa apara in ordinea trimiterii
        return messageDTOs;
    }
    public ResponseEntity<Map<String,String>> deleteMessage(long id_mesaj){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals("admin")){
            messageRepository.deleteById(id_mesaj);
            log.info("Admin-ul a sters mesajul cu id-ul "+id_mesaj+".");
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
        else{
            log.info("Un utilizator neautorizat a incercat sa stearga mesajul cu id-ul "+id_mesaj+". Faptas:"+username);
            Map<String, String> body = new HashMap<>();
            body.put("detail","Doar administratorul are voie sa stearga mesaje.");
            return new ResponseEntity<>(body,HttpStatus.FORBIDDEN);
        }
    }
    public ResponseEntity<List<Message>> viewMessageAdmin(){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals("admin")){
            return new ResponseEntity<>(messageRepository.findAll(),HttpStatus.OK);
        }
        else{
            log.info("Un utilizator neautorizat a incercat sa vada informatii despre mesaje. Faptas:"+username);
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }
}
