package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.SODTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.ServiceOrder;

public class ServiceOrderMapper {

    // ENTITY → DTO
    public static SODTO toDTO(ServiceOrder so) {
        return SODTO.builder()
                .id(so.getId())
                .deviceId(so.getDevice()
                        .getId()
                )
                .entry_date(so.getEntry_date())
                .curent_State(so.getCurent_State())
                .status(so.getStatus())
                .defect_reported(so.getDefect_reported())
                .service_description(so.getService_description())
                .warranty_period(so.getWarranty_period())
                .build();

    }

    // DTO → ENTITY
    public static ServiceOrder toEntity(SODTO dto, Device device) {
        return ServiceOrder.builder()
                .device(device)
                .entry_date(dto.getEntry_date())
                .curent_State(dto.getCurent_State())
                .status(dto.getStatus())
                .defect_reported(dto.getDefect_reported())
                .service_description(dto.getService_description())
                .warranty_period(dto.getWarranty_period())
                .build();
    }



}
