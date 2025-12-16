package Model.Device;

import Model.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

public class Device {
    private String DeviceType;
    @Id
    private long id;
    private User owner;
    private String TroubleDescription;
    private LocalDate SignUpDate;
}
