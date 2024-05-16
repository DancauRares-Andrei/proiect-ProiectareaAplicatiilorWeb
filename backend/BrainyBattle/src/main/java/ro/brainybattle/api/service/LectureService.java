package ro.brainybattle.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.brainybattle.model.Epoch;
import ro.brainybattle.model.Lecture;
import ro.brainybattle.repository.EpochRepository;
import ro.brainybattle.repository.LectureRepository;


import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private EpochRepository epochRepository;
    private static final Logger log = LoggerFactory.getLogger(LectureService.class);
    public ResponseEntity<Lecture> getLecture(String epoch_name){
        Optional<Lecture> lecture=lectureRepository.findLectureByEpochName(epochRepository.findEpochByName(epoch_name));
        if(lecture.isPresent()) {
            Lecture lecture1=lecture.get();
            log.info("S-a accesat cursul epocii "+epoch_name+" de catre "+ SecurityContextHolder.getContext().getAuthentication().getName()+".");
            return new ResponseEntity<>(lecture.get(), HttpStatus.OK);
        }
        log.info("S-a accesat cursul epocii inexistente "+epoch_name+" de catre "+ SecurityContextHolder.getContext().getAuthentication().getName()+".");
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
