package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Repository.DeviceRepo;
import Kani0dev.ATM.Repository.SORepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SOService {
    private final SORepo ServiceOrderREPO;
    private final DeviceRepo deviceRepo;

    public SOService(SORepo serviceOrderREPO, DeviceRepo deviceRepo) {
        ServiceOrderREPO = serviceOrderREPO;
        this.deviceRepo = deviceRepo;
    }

    public List<ServiceOrder> gettAllSOFromDevice(long device_id){
        Device thisdevice = deviceRepo.findById(device_id).get();
        return  thisdevice.getSOs();
    }

    public List<ServiceOrder> addServiceTODevice(long device_id, ServiceOrder serviceorder){
        Optional<Device> device = deviceRepo.findById(device_id);

        device.ifPresent(value -> value.addSO(serviceorder));

        serviceorder.setDevice_id(device.get());

        ServiceOrderREPO.save(serviceorder);
        return  device.get().getServiceorder();
    }

    public ServiceOrder rmServiceFromDevice(long device_id, ServiceOrder serviceOrder){
        ServiceOrderREPO.delete(serviceOrder);
        return serviceOrder;
    }

    public ServiceOrder editSO(long so_id, ServiceOrder serviceOrder){
            ServiceOrder thisService = ServiceOrderREPO.findById(so_id).get();


            thisService.setId(so_id);
            return ServiceOrderREPO.save(serviceOrder);
    }
}
