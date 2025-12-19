package Model.User;

import Model.Device.Device;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "Clients")
@SuperBuilder
public class ClientUser extends User {

    @Nullable
    private List<Device> deviceList;

    @Override
    public void setIsActive(Boolean isActive) {
        super.setIsActive(true);
    }
}
