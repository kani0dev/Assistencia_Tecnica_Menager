package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Model.User.ClientUser;
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

    public List<ServiceOrder> gettAllSOFromDevice(long device_id){
        Device thisdevice = deviceRepo.findById(device_id).get();
        return  thisdevice.getSOs();
    }

    public List<ServiceOrder> addServiceTODevice(long device_id, ServiceOrder serviceorder){
        Optional<Device> device = deviceRepo.findById(device_id);

        device.ifPresent(value -> value.addSO(serviceorder));

        serviceorder.setDevice_id(device.get());

        if (serviceorder.getEntry_date() == null) {
            serviceorder.setEntry_date(LocalDateTime.now().toString());
        } else {
            serviceorder.setEntry_date(serviceorder.getEntry_date());
        }

        ServiceOrderREPO.save(serviceorder);
        return  device.get().getServiceorder();
    }

    public ServiceOrder rmServiceFromDevice(long device_id, long so_id){
        ServiceOrder serviceOrder = ServiceOrderREPO.findById(so_id).get();
        Device device = deviceRepo.findById(device_id).get();
        device.rmSo(serviceOrder);
        ServiceOrderREPO.delete(serviceOrder);
        return serviceOrder;
    }

    public ServiceOrder editSO(long deviceId,long so_id, ServiceOrder serviceOrder){
            Optional <ServiceOrder> thisService = ServiceOrderREPO.findById(so_id);

            if(thisService.isPresent()){
                Device thisdevice = deviceRepo.findById(deviceId).get();
                serviceOrder.setDevice_id(thisdevice);
                serviceOrder.setId(so_id);
                return ServiceOrderREPO.save(serviceOrder);
            }
            return  null;
    }
}
