package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.DTO.SODTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;

import java.util.stream.Collectors;

public class DeviceMapper {

    // DTO → ENTITY (CREATE)
    public static Device map(DeviceDTO dto, ClientUser owner) {
        Device device = new Device();

        device.setOwner(owner);
        device.setType(dto.getType());
        device.setBrand(dto.getBrand());
        device.setModel(dto.getModel());
        device.setSerialNumber(dto.getSerialNumber());
        device.setColor(dto.getColor());
        device.setObservations(dto.getObservations());
        device.setSignUpDate(dto.getSignUpDate());

        return device;
    }

    // ENTITY → DTO (GERAL + SERVICE ORDERS)
    public static DeviceDTO map(Device device) {
        DeviceDTO dto = new DeviceDTO();

        dto.setId(device.getId());
        dto.setOwnerid(device.getOwner().getId());
        dto.setType(device.getType());
        dto.setBrand(device.getBrand());
        dto.setModel(device.getModel());
        dto.setSerialNumber(device.getSerialNumber());
        dto.setColor(device.getColor());
        dto.setObservations(device.getObservations());
        dto.setSignUpDate(device.getSignUpDate());

        // Service Orders (se existir)
        if (device.getSOs() != null) {
            dto.setServiceOrders(
                    device.getSOs()
                            .stream()
                            .map(ServiceOrderMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
