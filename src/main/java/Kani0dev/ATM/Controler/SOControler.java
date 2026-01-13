package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Service.SOService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController @RequestMapping("/device/so/")
public class SOControler {

    private final SOService serviceSO;

    public SOControler(SOService serviceSO) {
        this.serviceSO = serviceSO;
    }

    @GetMapping("list/{device_id}")
    public List<ServiceOrder> GetServiceOrders(@PathVariable long device_id){
        return serviceSO.gettAllSOFromDevice(device_id);
    }

    @PostMapping("add/{device_id}")
    public List<ServiceOrder> addServiceToDevice(@RequestBody ServiceOrder so,@PathVariable long device_id){
        return serviceSO.addServiceTODevice(device_id,so);
    }

    @DeleteMapping("rm/{device_id}@{so_id}")
    public void DeletService(@PathVariable Long so_id,@PathVariable long device_id){
        serviceSO.rmServiceFromDevice(device_id,so_id);
    }

    @PutMapping("edit/{deviceId}@{so_id}")
    public ServiceOrder editAServiceOrder(@PathVariable long so_id,@PathVariable Long deviceId,@RequestBody ServiceOrder so){
        return serviceSO.editSO(deviceId,so_id,so);
    }
}
