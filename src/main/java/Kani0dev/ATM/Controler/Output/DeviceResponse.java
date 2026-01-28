package Kani0dev.ATM.Controler.Output;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.DTO.SODTO;
import lombok.Builder;

import java.util.List;

@Builder
public record DeviceResponse (
        Long id,
         Long ownerid ,
         String type,
         String brand,
         String model,
         String serialNumber,
         String color,
         String observations,
         String signUpDate,
         List<Long>serviceOrders
){
    public static DeviceResponse toResponse(DeviceDTO dto){
        return  DeviceResponse.builder()
                .id(dto.getId())
                .ownerid(dto.getOwnerid())
                .type(dto.getType())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .serialNumber(dto.getSerialNumber())
                .color(dto.getColor())
                .observations(dto.getObservations())
                .signUpDate(dto.getSignUpDate())
                .serviceOrders( dto.getServiceOrders().stream()
                        .map(SODTO::getDeviceId)
                        .toList()

                )
                .build();
    }
}
