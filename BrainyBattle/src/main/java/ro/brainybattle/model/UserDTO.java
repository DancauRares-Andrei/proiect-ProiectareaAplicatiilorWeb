package ro.brainybattle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDTO implements UserDetails {
    private String username;
    @Getter
    private String password;
    private List<GrantedAuthority> authorities;
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password1){
        password=password1;
    }
    public void setUsername(String username1){
        username=username1;
    }
    public UserDTO(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
