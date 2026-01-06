package Kani0dev.ATM.DTO;

import Kani0dev.ATM.Model.User.ClientUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeviceDTO {
    private Long id;

    private ClientUser owner;

    private String type;

    private String brand;

    private String model;

    private String serialNumber;
    private String color;

    private String observations;

    private String signUpDate;
    public Long getOwnerId() {
        return owner != null ? owner.getId() : null;
    }
}