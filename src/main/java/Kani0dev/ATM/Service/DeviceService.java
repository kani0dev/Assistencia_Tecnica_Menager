package Kani0dev.ATM.Service;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Mapper.DeviceMapper;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.DeviceRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                    .map(DeviceMapper :: toDto)
                    .collect(Collectors.toList());
    }
    //findbyid
    public DeviceDTO findDeviceById(long id){
        Device device = deviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device não encontrado"));

        return DeviceMapper.toDto(device);
    }
    //creatAdevice
    public DeviceDTO createnewDevice(DeviceDTO dto, ClientUser client) {
        if (dto == null) {
            return null;
        }

        Device device = DeviceMapper.toEntity(dto, client);
        device.setOwner(client);
        device.setSignUpDate(LocalDate.now().toString());

        Device saved = deviceRepo.save(device);

        return DeviceMapper.toDto(saved);
    }

    //DeletDevice
    public void deletDevice(long id){
        if(deviceRepo.existsById(id)){
            deviceRepo.deleteById(id);
        }
    }
    //edit
    public Device editDevice(DeviceDTO dto, long id) {

        Device device = deviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        // ❌ NÃO setar ID
        device.setType(dto.getType());
        device.setBrand(dto.getBrand());
        device.setModel(dto.getModel());
        device.setSerialNumber(dto.getSerialNumber());
        device.setColor(dto.getColor());
        device.setObservations(dto.getObservations());
        device.setSignUpDate(dto.getSignUpDate());



        Device saved = deviceRepo.save(device);
        return saved;
    }
}
