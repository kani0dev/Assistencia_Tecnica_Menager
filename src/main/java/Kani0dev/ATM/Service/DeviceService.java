package Kani0dev.ATM.Service;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Mapper.DeviceMapper;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.DeviceRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private final DeviceRepo deviceRepo;
    private Optional<Device> deviceMapper;

    public DeviceService(DeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }


    //getall
    public List<DeviceDTO> getallDevices(){
            List<Device> devices = deviceRepo.findAll();
            return devices.stream()
                    .map(DeviceMapper :: map)
                    .collect(Collectors.toList());
    }
    //findbyid
    public Device findDeviceByid(long id){
        Optional<Device> thisdevice = deviceRepo.findById(id);
        return  thisdevice.orElse(null);
    }
    //creatAdevice
    public DeviceDTO createnewDevice(DeviceDTO newdevice, ClientUser client){
        if (newdevice == null) {
            return new DeviceDTO();
        }

       Device device = DeviceMapper.map(newdevice);
        device.setOwner(client);
       deviceRepo.save(device);
       return DeviceMapper.map(device);
    }

    //DeletDevice
    public void deletDevice(long id){
        if(deviceRepo.existsById(id)){
            deviceRepo.deleteById(id);
        }
    }
    //edit
    public DeviceDTO editDevice(DeviceDTO dto, long id) {
        Device device = deviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        device.setId(dto.getId());
        device.setType(dto.getType());
        device.setSignUpDate(dto.getSignUpDate());
        device.setOwner(dto.getOwner());
        device.setObservations(dto.getObservations());

        // set outros campos editáveis aqui

        Device saved = deviceRepo.save(device);
        return DeviceMapper.map(saved);
    }



}
