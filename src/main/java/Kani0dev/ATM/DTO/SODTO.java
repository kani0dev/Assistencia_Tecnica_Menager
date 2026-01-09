package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.OS_State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SODTO {
    private UUID id ;
    private Device devide_id;
    private String entry_date;
    private String curent_State;
    private OS_State status;
    private String defect_reported;
    private String service_description;
    private String warranty_period;
}
