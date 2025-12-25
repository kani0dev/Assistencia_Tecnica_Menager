package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.DeviceRepo;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepo deviceRepo;

    public DeviceService(DeviceRepo deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    //getall
    public List<Device> getallDevices(){
        return deviceRepo.findAll();
    }
    //findbyid
    public Device findDeviceByid(long id){
        return deviceRepo.findById(id).orElseThrow();
    }
    //creatAdevice
    public Device createnewDevice(Device newdevice){
       if(newdevice == null){
           return newdevice;
       }
       return deviceRepo.save(newdevice);
    }

    //DeletDevice
    public void deletDevice(long id){
        if(deviceRepo.existsById(id)){
            deviceRepo.deleteById(id);
        }
    }
    //edit
    public Device editDevice(Device device, long id){
        Optional<Device> exitentdevice = deviceRepo.findById(id);
        if (exitentdevice.isPresent()){
            device.setId(id);
            deviceRepo.save(device);
            return device;
        }
        return null;
    }


}
