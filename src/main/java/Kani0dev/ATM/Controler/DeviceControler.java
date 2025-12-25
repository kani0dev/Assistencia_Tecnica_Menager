package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceControler {
    private final DeviceService serviceDevice;

    public DeviceControler(DeviceService serviceDevice) {
        this.serviceDevice = serviceDevice;
    }

    @GetMapping("/list")
    public List<Device> getalldevices(){    return serviceDevice.getallDevices();   }

    @GetMapping("/list/{id}")
    public Device findDeviceByid(@PathVariable long id){    return serviceDevice.findDeviceByid(id);  }

    @PostMapping("/add")
    public Device addnewDevice(@RequestBody Device device){
        return serviceDevice.createnewDevice(device);
    }

    @DeleteMapping("/rm/{id}")
    public void rmADevice(@PathVariable long id){  serviceDevice.deletDevice(id);}

    @PutMapping("/edit/{id}")
    public Device editDevice(@RequestBody Device device, @PathVariable long id){
        return serviceDevice.editDevice(device,id);
    }

    // client operation


}
