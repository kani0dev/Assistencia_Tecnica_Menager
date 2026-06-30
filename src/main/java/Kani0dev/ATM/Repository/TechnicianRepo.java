package Kani0dev.ATM.Repository;

import Kani0dev.ATM.Model.User.TechnicianUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechnicianRepo extends JpaRepository<TechnicianUser, Long> {
    Optional<TechnicianUser> findByName(String name);
}
