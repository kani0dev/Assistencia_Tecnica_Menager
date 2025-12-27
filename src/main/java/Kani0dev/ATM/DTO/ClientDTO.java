package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.User;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO extends User {
    private List<Device> deviceList = new ArrayList<>();
}
