package Kani0dev.ATM.Model.Device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@Builder
@Table(name = "service_order")
//logic confusion -  the order service will be binded in the  client or to a device
public class ServiceOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    @JsonIgnore
    private Device device;

    private String entry_date;
    private String curent_State;

    private String status;
    private String defect_reported;
    private String service_description;
    private String warranty_period;
}
