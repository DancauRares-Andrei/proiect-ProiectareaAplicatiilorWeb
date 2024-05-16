package ro.brainybattle.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import ro.brainybattle.access.TrivialAuthenticationManager;
import ro.brainybattle.model.Epoch;
import ro.brainybattle.model.User;
import ro.brainybattle.model.UserDTO;
import ro.brainybattle.model.UserGameStat;
import ro.brainybattle.repository.EpochRepository;
import ro.brainybattle.repository.UserGameStatRepository;
import ro.brainybattle.repository.UserRepository;

import java.util.*;

@Service
public class UACService {
    private static final Logger log = LoggerFactory.getLogger(UACService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGameStatRepository userGameStatRepository;
    @Autowired
    private EpochRepository epochRepository;
    @Autowired
    private TrivialAuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public ResponseEntity<Map<String,String>> createUser(User new_user){
        try {
            userRepository.save(new_user);
            log.info("Utilizator creat:"+new_user.getUsername());
            UserGameStat userGameStat=new UserGameStat();
            userGameStat.setUser(new_user);
            Epoch epoch=epochRepository.findEpochByName("Preistorica").get();
            userGameStat.setEpoch(epoch);
            userGameStat.setScore(0);
            userGameStatRepository.save(userGameStat);
            Map<String, String> body = new HashMap<>();
            body.put("detail","Utilizator creat cu succes.");
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        }
        catch (Exception e){
            log.info("A apărut o eroare la crearea unui user:"+new_user.getUsername());
            Map<String, String> body = new HashMap<>();
            body.put("detail","A apărut o eroare la crearea user-ului.");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
    }
    public Map<String,String> loginUser(UserDTO userlogin) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userlogin.getUsername(), userlogin.getPassword()));
        if (authentication.isAuthenticated()) {
            Map<String, String> body = new HashMap<>();
            body.put("token",jwtService.generateToken(userlogin.getUsername()));
            return body;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    public ResponseEntity<Map<String,String>> logout(String token) {
        log.info("Deconectare utilizator "+SecurityContextHolder.getContext().getAuthentication().getName()+".");
        JwtService.addToBlacklist(token);
        log.info(token);
        SecurityContextHolder.clearContext();
        Map<String, String> body = new HashMap<>();
        body.put("detail","Logout reușit.");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    public ResponseEntity<List<User>> getUsers() {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        log.info(SecurityContextHolder.getContext().getAuthentication().toString());
        if(username.equals("admin")) {
            List<User> result = new ArrayList<User>();
            log.info("S-a accesat baza de utilizatori de catre " + SecurityContextHolder.getContext().getAuthentication().getName()+".");
            userRepository.findAll().forEach(result::add); // <=> x -> result.add(x)
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        else{
            log.info("Utilizatorul "+username+" a incercat sa obtina informatiile din tabelul utilizatorilor.");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
    public ResponseEntity<User> getUser(long id) {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals("admin")) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                log.info("Admin-ul a obtinut cu succes informatii despre utilizatorul cu id-ul "+id+".");
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            } else {
                log.info("Admin-ul nu a obtinut cu succes informatii despre utilizatorul cu id-ul "+id+", pentru ca nu exista.");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        else{
            log.info("Utilizatorul "+username+" a incercat sa obtina informatii despre utilizatorul cu id-ul "+id+".");
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
    public ResponseEntity<Map<String,String>> deleteUser(long id) {
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        if(username.equals("admin")) {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                userGameStatRepository.deleteByUsername(user.get().getUsername());
                userRepository.delete(user.get());
                log.info("S-a sters utilizatorul " + user.get().getUsername()+".");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                Map<String, String> body = new HashMap<>();
                body.put("detail","Utilizatorul nu a fost gasit.");
                log.info("S-a incercat stergerea utilizatorului cu id-ul " + id + ", dar a esuat.");
                return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
            }
        }
        else{
            log.info("Utilizatorul "+username+" a incercat sa stearga utilizatorul cu id-ul "+id+".");
            Map<String, String> body = new HashMap<>();
            body.put("detail","Doar administratorul are acces.");
            return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
        }
    }
    public ResponseEntity<Map<String,String>> updateUser(String new_pass) {
        String utilizator_curent=SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<User> user=userRepository.findByUsername(utilizator_curent);
            if(user.isPresent()) {
                userRepository.updatePassword(new_pass,utilizator_curent);
                log.info("Utilizatorul "+utilizator_curent+" a modificat parola utilizatorului "+utilizator_curent);
                Map<String, String> body = new HashMap<>();
                body.put("detail","Parola actualizata cu succes.");
                return new ResponseEntity<>(body,HttpStatus.OK);
            }
            else{
                log.info("Adminul a incercat sa modifice parola lui "+utilizator_curent+", care nu exista.");
                Map<String, String> body = new HashMap<>();
                body.put("detail","Utilizatorul nu a fost gasit.");
                return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
            }
    }
}
