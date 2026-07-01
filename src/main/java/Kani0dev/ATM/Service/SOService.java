package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Repository.DeviceRepo;
import Kani0dev.ATM.Repository.SORepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<ServiceOrder> gettAllSOFromDevice(Long deviceId){
        if (deviceId != null) {
            return ServiceOrderREPO.findByDevice_Id(deviceId);
        }
        return ServiceOrderREPO.findAll();
    }

    public List<ServiceOrder> addServiceTODevice(long deviceId, ServiceOrder serviceorder){
        Device device = deviceRepo.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        device.addSO(serviceorder);

        serviceorder.setDevice(device);

        if (serviceorder.getEntry_date() == null) {
            serviceorder.setEntry_date(LocalDateTime.now().toString());
        }

        ServiceOrderREPO.save(serviceorder);
        return device.getServiceorder();
    }

    public void rmServiceFromDevice(long soId){
        ServiceOrder serviceOrder = ServiceOrderREPO.findById(soId)
                .orElseThrow(() -> new RuntimeException("Service order not found"));
        Device device = serviceOrder.getDevice();
        if (device != null) {
            device.rmSo(serviceOrder);
        }
        ServiceOrderREPO.delete(serviceOrder);
    }

    public ServiceOrder editSO(long soId, long deviceId, ServiceOrder serviceOrder){
        Optional<ServiceOrder> thisService = ServiceOrderREPO.findById(soId);

        if(thisService.isPresent()){
            Device device = deviceRepo.findById(deviceId)
                    .orElseThrow(() -> new RuntimeException("Device not found"));
            serviceOrder.setDevice(device);
            serviceOrder.setId(soId);
            return ServiceOrderREPO.save(serviceOrder);
        }
        return null;
    }
}
