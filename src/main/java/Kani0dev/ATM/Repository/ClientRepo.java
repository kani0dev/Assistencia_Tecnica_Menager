package Kani0dev.ATM.Repository;

import Kani0dev.ATM.Model.User.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<ClientUser, Long> {
    ClientUser id(Long id);
}
