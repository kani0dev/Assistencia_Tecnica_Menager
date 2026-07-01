package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Controler.Output.ClientResponse;
import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Mapper.ClientMapper;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*",  methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
@PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
public class ClientControler {
    private final ClienteService serviceClient;
    private final ClientMapper mapper;

    public ClientControler(ClienteService clienteService, ClientMapper mapper) {
        this.serviceClient = clienteService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> ShowAllClients(){
        List<ClientResponse> reponses = serviceClient.ListClientUsers().stream().map(ClientResponse::toResponse).toList();
        return ResponseEntity.ok(reponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse>  ShowClientById(@PathVariable long id){
        ClientUser clientUser = serviceClient.findtById(id);
        ClientDTO dto = mapper.toDTO(clientUser);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ClientResponse.toResponse(dto));
    }

    @PostMapping
    public ResponseEntity<ClientDTO>  AddNewCLient(@RequestBody ClientDTO client){
        ClientDTO dto = serviceClient.SingUpClient(client);
        return  ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/{id}")
    public void DeletClient(@PathVariable long id){
        serviceClient.HardDelet(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientUser> toggleClientActive(@PathVariable long id, @RequestBody Map<String, Boolean> body){
        boolean active = body.getOrDefault("active", true);
        ClientUser updated = serviceClient.setActiveStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientUser> AlterClient(@PathVariable long id, @RequestBody ClientUser client){
        ClientUser clientUser = serviceClient.UpdateClient(client, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(clientUser);
    }

    // devices sub-resources

    @PostMapping("/{clientId}/devices/{deviceId}")
    public ClientUser AddDevicetoClient(@PathVariable long clientId, @PathVariable long deviceId){
        return serviceClient.addExistentDevice(clientId, deviceId);
    }

    @DeleteMapping("/{clientId}/devices/{deviceId}")
    public ClientUser removeDevice(@PathVariable long clientId, @PathVariable long deviceId){
        return serviceClient.removeADevice(clientId, deviceId);
    }

    @GetMapping("/{clientId}/devices")
    public List<Device> SeeDevices(@PathVariable long clientId){
        return serviceClient.seeAllDevices(clientId);
    }
}
