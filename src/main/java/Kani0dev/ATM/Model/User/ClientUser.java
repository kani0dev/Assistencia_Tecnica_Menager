package Kani0dev.ATM.Model.User;

import Kani0dev.ATM.Model.Device.Device;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "Clients")
@SuperBuilder
public class ClientUser extends User {

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Device> deviceList;

    @Override
    public void setIsActive(Boolean isActive) {
        super.setIsActive(true);
    }
}
