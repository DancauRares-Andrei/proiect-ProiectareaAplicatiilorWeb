package ro.brainybattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.brainybattle.model.Epoch;
import ro.brainybattle.model.Lecture;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    @Query("SELECT l FROM Lecture l where l.epoch=:epoch")
    Optional<Lecture> findLectureByEpochName(Optional<Epoch> epoch);
}
