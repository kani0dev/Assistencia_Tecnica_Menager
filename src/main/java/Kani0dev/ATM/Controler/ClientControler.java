package Kani0dev.ATM.Controler;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Service.ClienteService;
import ch.qos.logback.core.net.server.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*",  methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ClientControler {
    private final ClienteService serviceClient;

    public ClientControler(ClienteService clienteService) {
        this.serviceClient = clienteService;
    }

    @GetMapping("/")
    public String Test(){return "chegamos";}

    @GetMapping("/list")
    public List<ClientDTO> ShowAllClients(){
        return serviceClient.ListClientUsers();
    }

    @GetMapping("/list/{id}")
    public ClientUser ShowClientById(@PathVariable long id){
        return serviceClient.findtById(id);
    }

    @PostMapping("/add")
    public ClientDTO AddNewCLient(@RequestBody ClientDTO client){
        return  serviceClient.SingUpClient(client);
    }

    @DeleteMapping("/remove/{id}")
    //hard delet
    public void DeletClient(@PathVariable long id){
        serviceClient.HardDelet(id);
    }
    //soft delet

    @PutMapping("/deactivate/{id}")
    public ClientUser DeactiveClient(@PathVariable long id){
        return serviceClient.deactivate(id,false);
    }
    @PutMapping("/activate/{id}")
    public ClientUser ActivateClient(@PathVariable long id){
        return  serviceClient.activate(id,true);
    }

    @PutMapping("/edit/{id}")
    public ClientUser AlterClient(@PathVariable long id,@RequestBody ClientUser client){
        return serviceClient.UpdateClient(client,id);
    }

    // devices methods;

    @PostMapping("/add-device/{client_id}@{device_id}")
    public ClientUser AddDevicetoClient(@PathVariable long client_id, @PathVariable long device_id){
        return serviceClient.addExistentDevice(client_id,device_id);
    }

    @DeleteMapping("/rm-device/{client_id}@{device_id}")
    public ClientUser removeDevice(@PathVariable long client_id, @PathVariable long device_id){
        return serviceClient.removeADevice(client_id,device_id);
    }

    @GetMapping("/get-device/{client_id}")
    public List<Device> SeeDevices(@PathVariable long client_id){
        return serviceClient.seeAllDevices(client_id);
    }
}
