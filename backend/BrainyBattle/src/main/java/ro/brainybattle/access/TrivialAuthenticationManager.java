package ro.brainybattle.access;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ro.brainybattle.model.User;
import ro.brainybattle.repository.UserRepository;

@Component
public class TrivialAuthenticationManager implements AuthenticationManager {
	@Autowired
	UserRepository userRepository;
	static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	static {
		// Examples of GrantedAuthority - ROLE_USER, READ_AUTHORITY, WRITE_PRIVILEGE, CAN_EXECUTE_AS_ROOT
		// protected by authority vs protected by role
		AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
	}

	/**
	 * Will authenticate if the username and password are the same.
	 * If assigns the role ROLE_USER to each user.
	 */
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		if(username=="admin"){
			// Verificare în baza de date
			Optional<User> userOptional = userRepository.findByUsername(username);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				if (password.equals(user.getPassword())) {
					List<GrantedAuthority> AUTHORITIES_ADMIN = new ArrayList<GrantedAuthority>();
					AUTHORITIES_ADMIN.add(new SimpleGrantedAuthority("ROLE_USER"));
					AUTHORITIES_ADMIN.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
					return new UsernamePasswordAuthenticationToken(username, password, AUTHORITIES_ADMIN);
				} else {
					throw new BadCredentialsException("Parolă incorectă");
				}
			} else {
				throw new UsernameNotFoundException("Utilizatorul nu a fost găsit: " + username);
			}
		}
		else {
			// Verificare în baza de date
			Optional<User> userOptional = userRepository.findByUsername(username);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				if (password.equals(user.getPassword())) {
					return new UsernamePasswordAuthenticationToken(username, password, AUTHORITIES);
				} else {
					throw new BadCredentialsException("Parolă incorectă");
				}
			} else {
				throw new UsernameNotFoundException("Utilizatorul nu a fost găsit: " + username);
			}
		}
	}
}