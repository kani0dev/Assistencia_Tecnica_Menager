package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.SODTO;
import Kani0dev.ATM.Model.Device.ServiceOrder;



public class SOMapper {
    public static ServiceOrder map(SODTO dto) {
        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setId(dto.getId());
        serviceOrder.setDevide_id(dto.getDevide_id());
        serviceOrder.setEntry_date(dto.getEntry_date());
        serviceOrder.setCurent_State(dto.getCurent_State());
        serviceOrder.setStatus(dto.getStatus());
        serviceOrder.setDefect_reported(dto.getDefect_reported());
        serviceOrder.setService_description(dto.getService_description());
        serviceOrder.setWarranty_period(dto.getWarranty_period());

        return serviceOrder;
    }


    public static SODTO map(ServiceOrder entity) {
        SODTO dto = new SODTO();

        dto.setId(entity.getId());
        dto.setDevide_id(entity.getDevide_id());
        dto.setEntry_date(entity.getEntry_date());
        dto.setCurent_State(entity.getCurent_State());
        dto.setStatus(entity.getStatus());
        dto.setDefect_reported(entity.getDefect_reported());
        dto.setService_description(entity.getService_description());
        dto.setWarranty_period(entity.getWarranty_period());

        return dto;
    }
}
