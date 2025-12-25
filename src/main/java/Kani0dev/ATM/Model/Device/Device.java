package Kani0dev.ATM.Model.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private ClientUser owner;

    private String TroubleDescription;
    private String SignUpDate;
}
