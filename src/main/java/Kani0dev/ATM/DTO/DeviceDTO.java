package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.User.ClientUser;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceDTO {

    private String DeviceType;
    private long id;
    private ClientUser owner_id;
    private String TroubleDescription;
    private String SignUpDate;
    public Long getOwnerId() {
        return owner_id != null ? owner_id.getId() : null;
    }
}