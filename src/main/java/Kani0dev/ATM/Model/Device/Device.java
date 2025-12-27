package Kani0dev.ATM.Model.Device;
import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.User.ClientUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Device {
    private String DeviceType;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private  ClientUser owner_id;

    private String TroubleDescription;
    private String SignUpDate;

    @JsonProperty("owner_id")
    public Long getOwnerId() {
        return owner_id != null ? owner_id.getId() : null;
    }


}

// todo review all relationatiom with owner atribute
