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
// isso ta ''errado'' , o dto é uma representaçao estatica de um objeto ele é uma simples abstraçao
// quando ele estende ou herda algo ele tambem recebe as prorpriedades dessa classe
// nesse caso aq por extender o user ele tambem ganha propriedades das notations de User oque é contra design.
public class ClientDTO extends User {
        private List<Device> deviceList = new ArrayList<>();
        public void addDevice(Device device){
        this.deviceList.add(device);
    }
        public void addDevices(List<Device> ids) { this.deviceList.addAll(ids);}
    }

