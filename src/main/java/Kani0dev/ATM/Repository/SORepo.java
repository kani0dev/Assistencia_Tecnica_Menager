package Kani0dev.ATM.Repository;

import Kani0dev.ATM.Model.Device.ServiceOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.config.RepositoryConfiguration;

import java.util.List;

public interface SORepo extends JpaRepository<ServiceOrder , Long>{
    List<ServiceOrder> findByDevice_Id(Long id);
}
