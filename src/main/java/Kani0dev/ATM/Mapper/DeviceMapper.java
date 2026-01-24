package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.DTO.SODTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Model.User.ClientUser;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DeviceMapper {

    // DTO → ENTITY (CREATE)
    public static Device toEntity(DeviceDTO dto, ClientUser owner) {


        Device device = Device.builder()
                .id(dto.getId())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .color(dto.getColor())
                .type(dto.getType())
                .observations(dto.getObservations())
                .serialNumber(dto.getSerialNumber())
                .signUpDate(dto.getSignUpDate())
                .color(dto.getColor())
                .owner(owner)
                .build();

        List<ServiceOrder> orders = Optional.ofNullable(dto.getServiceOrders())
                .orElseGet(ArrayList::new)
                .stream()
                    .map(sodto -> ServiceOrderMapper.toEntity(sodto,device))
                .toList();

        device.setServiceorder(orders);

        return device;
    }

    // ENTITY → DTO (GERAL + SERVICE ORDERS)
    public static DeviceDTO toDto(Device device) {

        return DeviceDTO.builder()
                .id(device.getId())
                .ownerid(device.getOwnerId())
                .type(device.getType())
                .brand(device.getBrand())
                .model(device.getModel())
                .color(device.getColor())
                .signUpDate(device.getSignUpDate())
                .observations(device.getObservations())
                .serialNumber(device.getSerialNumber())
                .serviceOrders(
                        device.getServiceorder()
                        .stream()
                        .map(ServiceOrderMapper::toDTO)
                        .toList()
                )
                .build();
    }
}
