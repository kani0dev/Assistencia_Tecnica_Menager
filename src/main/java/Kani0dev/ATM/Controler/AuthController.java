package Kani0dev.ATM.Controler;

import Kani0dev.ATM.DTO.RegisterRequest;
import Kani0dev.ATM.Model.User.Role;
import Kani0dev.ATM.Model.User.TechnicianUser;
import Kani0dev.ATM.Repository.TechnicianRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TechnicianRepo repo;
    private final PasswordEncoder encoder;

    public AuthController(TechnicianRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (repo.findByName(request.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Username already exists"));
        }

        Role role = request.getRole() != null ? request.getRole() : Role.TECHNICIAN;

        TechnicianUser user = TechnicianUser.builder()
                .name(request.getName())
                .password(encoder.encode(request.getPassword()))
                .role(role)
                .isActive(true)
                .build();

        repo.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("id", user.getId(), "name", user.getName(), "role", user.getRole()));
    }
}
