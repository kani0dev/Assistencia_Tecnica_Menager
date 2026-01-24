package Kani0dev.ATM.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SODTO {
    private long id;
    private Long deviceId;
    private String entry_date;
    private String curent_State;
    private String status;
    private String defect_reported;
    private String service_description;
    private String warranty_period;
}
