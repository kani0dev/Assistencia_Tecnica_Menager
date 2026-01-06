package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Model.Device.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {
    public static Device map(DeviceDTO devicedto){
        Device device = new Device();

        device.setId(devicedto.getId());
        device.setOwner(devicedto.getOwner());
        device.setType(devicedto.getType());
        device.setBrand(devicedto.getBrand());
        device.setModel(devicedto.getModel());
        device.setSerialNumber(devicedto.getSerialNumber());
        device.setColor(devicedto.getColor());
        device.setObservations(devicedto.getObservations());
        device.setSignUpDate(devicedto.getSignUpDate());

        device.setObservations(devicedto.getObservations());


        return device;
    }

    public static DeviceDTO map(Device device){
        DeviceDTO deviceDTO = new DeviceDTO();

        deviceDTO.setId(device.getId());
        deviceDTO.setOwner(device.getOwner());
        deviceDTO.setType(device.getType());
        deviceDTO.setBrand(device.getBrand());
        deviceDTO.setModel(device.getModel());
        deviceDTO.setSerialNumber(device.getSerialNumber());
        deviceDTO.setColor(device.getColor());
        deviceDTO.setObservations(device.getObservations());
        deviceDTO.setSignUpDate(device.getSignUpDate());

        return deviceDTO;
    }
}
