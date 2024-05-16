package ro.brainybattle.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.brainybattle.model.UserGameStat;
import ro.brainybattle.model.UserGameStatDTO;
import ro.brainybattle.repository.UserGameStatRepository;

@Service
public class UserGameStatService {
    private static final Logger log = LoggerFactory.getLogger(UserGameStatService.class);
    @Autowired
    UserGameStatRepository userGameStatRepository;
    public ResponseEntity<UserGameStatDTO> getUserDetails(){
        try{
            UserGameStatDTO userGameStatDTO=userGameStatRepository.getUserGameStat(SecurityContextHolder.getContext().getAuthentication().getName());
            log.info("S-au accesat detaliile utilizatorului "+SecurityContextHolder.getContext().getAuthentication().getName()+".");
            return new ResponseEntity<>(userGameStatDTO, HttpStatus.OK);
        }
        catch (Exception ex){
            ex.printStackTrace();
            log.info("S-a încercat accesat detaliilor utilizatorului "+SecurityContextHolder.getContext().getAuthentication().getName()+", dar a eșuat.");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> updateUserScore(Integer add_score){
        try {
            userGameStatRepository.updateScore(add_score, SecurityContextHolder.getContext().getAuthentication().getName());
            log.info("S-a actualizat scorul utilizatorului "+SecurityContextHolder.getContext().getAuthentication().getName()+", adunandu-se "+add_score.toString()+".");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            ex.printStackTrace();
            log.info("S-a încercat actualizarea scorulului utilizatorului "+SecurityContextHolder.getContext().getAuthentication().getName()+" la "+add_score.toString()+", dar a eșuat.");
            return new ResponseEntity<>("Nu s-a putut actualiza scorul!",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<String> updateUserEpoch(String new_epoch){
        try {
            userGameStatRepository.updateEpoch(new_epoch, SecurityContextHolder.getContext().getAuthentication().getName());
            log.info("S-a actualizat epoca utilizatorului "+SecurityContextHolder.getContext().getAuthentication().getName()+" la "+new_epoch+".");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            log.info("S-a încercat actualizarea epocii utilizatorului "+SecurityContextHolder.getContext().getAuthentication().getName()+" la "+new_epoch+", dar a eșuat.");
            return new ResponseEntity<>("Nu s-a putut actualiza epoca! Epoca introdusă nu există!",HttpStatus.CONFLICT);
        }
    }
}