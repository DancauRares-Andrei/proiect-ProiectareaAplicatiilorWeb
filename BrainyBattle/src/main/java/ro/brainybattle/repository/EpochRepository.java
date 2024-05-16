package ro.brainybattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.brainybattle.model.Epoch;

import java.util.Optional;

@Repository
public interface EpochRepository extends JpaRepository<Epoch, Integer> {
    @Query("SELECT e FROM Epoch e where e.epoch_name=:epoch_name")
    Optional<Epoch> findEpochByName(String epoch_name);


}
