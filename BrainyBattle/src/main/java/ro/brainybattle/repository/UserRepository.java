package ro.brainybattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.brainybattle.model.User;

import jakarta.transaction.Transactional;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Iterable<User> findAllByUsername(@Param("username") String username);
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<User> findByUsername(@Param("username") String username);
	@Query("SELECT u FROM User u WHERE u.user_id = :id")
	Optional<User> findById(@Param("id") long id);
	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.password=:new_pass where u.username=:username")
	void updatePassword(String new_pass,String username);
}
