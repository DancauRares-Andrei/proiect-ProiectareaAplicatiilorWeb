package ro.brainybattle.access;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.brainybattle.model.User;
import ro.brainybattle.model.UserDTO;
import ro.brainybattle.repository.UserRepository;

import java.util.Optional;

@Service
@EnableJpaRepositories
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = repository.findByUsername(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserDTO::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

   /* public String addUser(User userInfo) {
        val encoder=new BCryptPasswordEncoder();
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }*/


}