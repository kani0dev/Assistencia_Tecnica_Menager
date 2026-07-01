package Kani0dev.ATM.Model.Device;

import Kani0dev.ATM.Model.User.ClientUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore
    private ClientUser owner;

    @OneToMany(mappedBy = "device",cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<ServiceOrder> serviceorder = new ArrayList<>();

    public void addSO(ServiceOrder so){
        this.serviceorder.add(so);
    }
    public void rmSo(ServiceOrder so){
        this.serviceorder.remove(so);
    }public List<ServiceOrder> getSOs(){
        return  this.serviceorder;
    }

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(unique = true)
    private String serialNumber;
    private String color;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(nullable = false)
    private String signUpDate;
    @JsonProperty("owner")
    public Long getOwnerId() {
        return owner != null ? owner.getId() : null;
    }
}