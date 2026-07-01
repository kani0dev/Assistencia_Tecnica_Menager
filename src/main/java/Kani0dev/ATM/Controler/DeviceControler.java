package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Controler.Output.DeviceResponse;
import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Mapper.DeviceMapper;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.ClientRepo;
import Kani0dev.ATM.Service.DeviceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/devices")
@PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")

public class DeviceControler {
    private final DeviceService serviceDevice;
    private final ClientRepo clientRepo;

    public DeviceControler(DeviceService serviceDevice, ClientRepo clientRepo) {
        this.serviceDevice = serviceDevice;
        this.clientRepo = clientRepo;
    }

    @GetMapping
    public List<DeviceResponse> getalldevices(){
        List<DeviceResponse> devices = serviceDevice.getallDevices()
                .stream()
                .map(DeviceResponse::toResponse)
                .toList();

        return devices;
    }

    @GetMapping("/{id}")
    public DeviceResponse findDeviceByid(@PathVariable long id){
        return DeviceResponse.toResponse(serviceDevice.findDeviceById(id));
    }

    @PostMapping
    public DeviceDTO addnewDevice(@RequestBody DeviceDTO device) {
        Long clientId = device.getOwnerid();
        ClientUser client = clientRepo.findById(clientId)
                .orElse(null);

        return serviceDevice.createnewDevice(device, client);
    }

    @DeleteMapping("/{id}")
    public void rmADevice(@PathVariable long id){  serviceDevice.deletDevice(id);}

    @PutMapping("/{id}")
    public Device editDevice(@RequestBody DeviceDTO device, @PathVariable long id){
        return serviceDevice.editDevice(device,id);
    }

}
