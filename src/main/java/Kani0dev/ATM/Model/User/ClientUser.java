package Kani0dev.ATM.Model.User;

import Kani0dev.ATM.Model.Device.Device;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "Clients")
@SuperBuilder
@Getter
@Setter
public class ClientUser extends User {

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Device> deviceList = new ArrayList<>();

    public void addDevice(Device device) {
        this.deviceList.add(device);
        device.setOwner(this);
    }
    public void rmDevice(Device device){
        this.deviceList.remove(device);
        device.setOwner(null);
    }


    public List<Device> getAllDevice(){
        return  deviceList;
    }

}
