package ro.brainybattle.model;

import jakarta.persistence.*;
import lombok.Getter;

// https://www.codejava.net/frameworks/spring-boot/spring-boot-security-role-based-authorization-tutorial

//javax.persistence.* <= spring-boot-starter-data-jpa
@Entity // hibernate will create a table out of this class
//@Table(name = "\"user\"")
public class User {

	@Id
	@Column(name ="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;
	@Getter
	@Column(unique=true)
	private String	username;
	@Getter
	private String	password;
	@Getter
	private String	email;

	// required for UserRepository @Query
	public User() {
	}
	//lombok
	public User(long id, String username, String password, String email) {
		super();
		this.user_id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	// we need public getters, otherwise @GetMapping return a list of empty objects
	
	public long getId() {
		return user_id;
	}

	public Object[] toObjectArray() {
		return new Object[] { username, password, email };
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, email=%s]", user_id, username, password, email);
	}

}
