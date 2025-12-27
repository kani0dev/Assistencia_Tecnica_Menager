package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Model.Device.Device;

public class DeviceMapper {
    public static Device map(DeviceDTO devicedto){
        Device device = new Device();

        device.setId(devicedto.getId());
        device.setDeviceType(devicedto.getDeviceType());
        device.setSignUpDate(devicedto.getSignUpDate());
        device.setOwner_id(devicedto.getOwner_id());
        device.setTroubleDescription(devicedto.getTroubleDescription());

        return device;
    }

    public static DeviceDTO map(Device device){
        DeviceDTO deviceDTO = new DeviceDTO();

        deviceDTO.setId(device.getId());
        deviceDTO.setDeviceType(device.getDeviceType());
        deviceDTO.setSignUpDate(device.getSignUpDate());
        deviceDTO.setOwner_id(device.getOwner_id());
        deviceDTO.setTroubleDescription(device.getTroubleDescription());

        return deviceDTO;
    }
}
