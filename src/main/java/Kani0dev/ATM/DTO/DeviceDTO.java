package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.Device.ServiceOrder;
import Kani0dev.ATM.Model.User.ClientUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeviceDTO {
    private Long id;

    private Long ownerid ;
    private String type;

    private String brand;

    private String model;

    private String serialNumber;
    private String color;

    private String observations;

    private String signUpDate;
    private List<SODTO> serviceOrders;
}