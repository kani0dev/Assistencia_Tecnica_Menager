package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.User.TechnicianUser;
import Kani0dev.ATM.Repository.TechnicianRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final TechnicianRepo repo;

    public UserDetailsServiceImpl(TechnicianRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TechnicianUser user = repo.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(
                user.getName(),
                user.getPassword(),
                user.getIsActive() != null ? user.getIsActive() : true,
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }
}
