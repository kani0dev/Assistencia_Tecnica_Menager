package Repository;

import Model.User.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<ClientUser, Long> {
    ClientUser id(Long id);
}
