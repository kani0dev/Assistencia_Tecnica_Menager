package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.SODTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.ServiceOrder;

public class ServiceOrderMapper {

    // ENTITY → DTO
    public static SODTO map(ServiceOrder so) {
        SODTO dto = new SODTO();

        dto.setId(so.getId());
        dto.setEntry_date(so.getEntry_date());
        dto.setCurent_State(so.getCurent_State());
        dto.setStatus(so.getStatus());
        dto.setDefect_reported(so.getDefect_reported());
        dto.setService_description(so.getService_description());
        dto.setWarranty_period(so.getWarranty_period());

        // 🔥 só o ID do device
        if (so.getDevice_id()!= null) {
            dto.setDeviceId(so.getDevice_id().getId());
        }

        return dto;
    }

    // DTO → ENTITY
    public static ServiceOrder map(SODTO dto, Device device) {
        ServiceOrder so = new ServiceOrder();

        // ❌ não setar ID se for CREATE
        so.setDevice_id(device);
        so.setEntry_date(dto.getEntry_date());
        so.setCurent_State(dto.getCurent_State());
        so.setStatus(dto.getStatus());
        so.setDefect_reported(dto.getDefect_reported());
        so.setService_description(dto.getService_description());
        so.setWarranty_period(dto.getWarranty_period());

        return so;
    }
}
