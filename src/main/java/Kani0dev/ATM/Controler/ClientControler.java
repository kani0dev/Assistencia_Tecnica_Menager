package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Controler.Output.ClientResponse;
import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Mapper.ClientMapper;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Service.ClienteService;
import ch.qos.logback.core.net.server.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*",  methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientControler {
    private final ClienteService serviceClient;
    private final ClientMapper mapper;

    public ClientControler(ClienteService clienteService, ClientMapper mapper) {
        this.serviceClient = clienteService;
        this.mapper = mapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientResponse>> ShowAllClients(){
        List<ClientResponse> reponses = serviceClient.ListClientUsers().stream().map(ClientResponse::toResponse).toList();
        return ResponseEntity.ok(reponses);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<ClientResponse>  ShowClientById(@PathVariable long id){
        ClientUser clientUser = serviceClient.findtById(id);
        ClientDTO dto = mapper.toDTO(clientUser);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ClientResponse.toResponse(dto));
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDTO>  AddNewCLient(@RequestBody ClientDTO client){
        ClientDTO dto = serviceClient.SingUpClient(client);
        return  ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/remove/{id}")
    //hard delet
    public void DeletClient(@PathVariable long id){
        serviceClient.HardDelet(id);
    }
    //soft delet

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ClientUser> DeactiveClient(@PathVariable long id){
        ClientUser deactivate = serviceClient.deactivate(id, false);
        return ResponseEntity.ok(deactivate);
    }
    @PutMapping("/activate/{id}")
    public ResponseEntity<ClientUser> ActivateClient(@PathVariable long id){
        ClientUser activate = serviceClient.activate(id, true);
        return  ResponseEntity.ok(activate);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity< ClientUser> AlterClient(@PathVariable long id,@RequestBody ClientUser client){
        ClientUser clientUser = serviceClient.UpdateClient(client, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientUser);
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
