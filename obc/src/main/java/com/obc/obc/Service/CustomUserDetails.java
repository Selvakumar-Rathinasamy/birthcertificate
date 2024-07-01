package com.obc.obc.Service;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
// import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.obc.obc.Entity.User;
public class CustomUserDetails implements UserDetails
{
    private User user;
    public CustomUserDetails(User user)
    {
        super();
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getRole();
    System.out.println("Role retrieved: " + role);
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        // return List.of(()->user.getRole());
        return Arrays.asList(authority);
    }

    public String getMoblie() {
        return user.getMoblie();
    }

    public String getLastname() {
        return user.getLastname();
    }

    public String getAddress() {
        return user.getAddress();
    }

    public LocalDate getRegdate() {
        return user.getRegdate();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getFirstname();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
