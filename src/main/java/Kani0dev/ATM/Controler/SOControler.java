package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Service.SOService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/service-orders")
@PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
public class SOControler {

    private final SOService serviceSO;

    public SOControler(SOService serviceSO) {
        this.serviceSO = serviceSO;
    }

    @GetMapping
    public List<ServiceOrder> GetServiceOrders(@RequestParam(required = false) Long deviceId){
        return serviceSO.gettAllSOFromDevice(deviceId);
    }

    @PostMapping
    public List<ServiceOrder> addServiceToDevice(@RequestBody ServiceOrder so, @RequestParam Long deviceId){
        return serviceSO.addServiceTODevice(deviceId, so);
    }

    @DeleteMapping("/{soId}")
    public void DeletService(@PathVariable Long soId){
        serviceSO.rmServiceFromDevice(soId);
    }

    @PutMapping("/{soId}")
    public ServiceOrder editAServiceOrder(@PathVariable Long soId, @RequestParam Long deviceId, @RequestBody ServiceOrder so){
        return serviceSO.editSO(soId, deviceId, so);
    }
}
