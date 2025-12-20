package Kani0dev.ATM.Repository;


import Kani0dev.ATM.Model.Device.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepo extends JpaRepository<Device , Long> {
}
