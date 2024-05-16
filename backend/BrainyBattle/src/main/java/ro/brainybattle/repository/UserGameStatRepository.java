package ro.brainybattle.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ro.brainybattle.model.Epoch;
import ro.brainybattle.model.User;
import ro.brainybattle.model.UserGameStat;
import ro.brainybattle.model.UserGameStatDTO;

import java.util.Optional;

public interface UserGameStatRepository extends JpaRepository<UserGameStat, User> {
    @Transactional
    @Modifying
    @Query("UPDATE UserGameStat u SET u.score=:new_score+u.score where u.user.username=:username")
    void updateScore(Integer new_score,String username);
    @Transactional
    @Modifying
    @Query("UPDATE UserGameStat u SET u.epoch.epoch_name=:new_epoch where u.user.username=:username")
    void updateEpoch(String new_epoch, String username);
    @Query("select new ro.brainybattle.model.UserGameStatDTO(u.score,u.epoch.epoch_name,u.user.username) from UserGameStat u where u.user.username=:username")
    UserGameStatDTO getUserGameStat(String username);
    @Transactional
    @Modifying
    @Query("DELETE FROM UserGameStat u where u.user.username=:username")
    void deleteByUsername(String username);
}