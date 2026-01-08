package Kani0dev.ATM.Controler;

import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.ClientRepo;
import Kani0dev.ATM.Service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/device")
public class DeviceControler {
    private final DeviceService serviceDevice;
    private final ClientRepo clientRepo;

    public DeviceControler(DeviceService serviceDevice, ClientRepo clientRepo) {
        this.serviceDevice = serviceDevice;
        this.clientRepo = clientRepo;
    }

    @GetMapping("/list")
    public List<DeviceDTO> getalldevices(){    return serviceDevice.getallDevices();   }

    @GetMapping("/list/{id}")
    public Device findDeviceByid(@PathVariable long id){    return serviceDevice.findDeviceByid(id);  }

    @PostMapping("/add-to/{clientId}")
    public DeviceDTO addnewDevice(@RequestBody DeviceDTO device,@PathVariable Long clientId){
        Optional<ClientUser> thisclient = clientRepo.findById(clientId);
        if(thisclient.isPresent()){
            return serviceDevice.createnewDevice(device,thisclient.get());
        }
        return serviceDevice.createnewDevice(device,null);
        }


    @DeleteMapping("/rm/{id}")
    public void rmADevice(@PathVariable long id){  serviceDevice.deletDevice(id);}

    @PutMapping("/edit/{id}")
    public DeviceDTO editDevice(@RequestBody DeviceDTO device, @PathVariable long id){
        return serviceDevice.editDevice(device,id);
    }

    // client operation


}
