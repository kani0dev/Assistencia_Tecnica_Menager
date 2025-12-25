package Kani0dev.ATM.Model.User;

import Kani0dev.ATM.Model.Device.Device;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "Clients")
@SuperBuilder
public class ClientUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Column(name = "client_id") long id;

    @OneToMany
    @JoinColumn(name = "client_id")
    private List<Device> deviceList = new ArrayList<>();

    public void addDevice(Device device) {
        this.deviceList.add(device);
        device.setOwner(this);
    }
    public List<Device> rmDevice(Device device){
        deviceList.remove(device);
        return deviceList;
    }
    public List<Device> getAllDevice(){
        return  deviceList;
    }

}
