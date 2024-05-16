package ro.brainybattle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.brainybattle.model.Message;

import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("SELECT m FROM Message m ORDER BY m.message_id DESC")
    Page<Message> findMessages(Pageable pageable);
    @Transactional
    @Modifying
    @Query("DELETE FROM Message m where m.message_id=:idMesaj")
    void deleteById(long idMesaj);
}
