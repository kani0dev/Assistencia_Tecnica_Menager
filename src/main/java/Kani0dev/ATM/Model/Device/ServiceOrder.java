package Kani0dev.ATM.Model.Device;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
//logic confusion -  the order service will be binded in the  client or to a device
public class ServiceOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private Device devide_id;

    private String entry_date;
    private String curent_State;

    @Enumerated(EnumType.STRING)
    private OS_State status;
    private String defect_reported;
    private String service_description;
    private String warranty_period;
}
