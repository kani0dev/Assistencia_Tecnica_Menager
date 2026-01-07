package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO extends User {
        private List<Long> deviceList = new ArrayList<>();
        public void addDevice(Device device){
        this.deviceList.add(device.getId());
    }
        public void addDevices(List<Long> ids) { this.deviceList.addAll(ids);}
    }

