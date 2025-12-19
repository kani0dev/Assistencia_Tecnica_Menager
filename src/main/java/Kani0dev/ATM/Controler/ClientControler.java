package Kani0dev.ATM.Controler;

import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Service.ClienteService;
import ch.qos.logback.core.net.server.Client;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientControler {
    private final ClienteService serviceClient;

    public ClientControler(ClienteService clienteService) {
        this.serviceClient = clienteService;
    }

    @GetMapping("/")
    public String Test(){return "chegamos";}

    @GetMapping("/list")
    public List<ClientUser> ShowAllClients(){
        return serviceClient.ListClientUsers();
    }

    @GetMapping("/list/{id}")
    public ClientUser ShowClientById(@PathVariable long id){
        return serviceClient.findtById(id);
    }

    @PostMapping("/add")
    public ClientUser AddNewCLient(@RequestBody ClientUser client){
        return  serviceClient.SingUpClient(client);
    }

    @DeleteMapping("/r/{id}")
    //hard delet
    public ClientUser DeletClient(@PathVariable long id){
        ClientUser deletedUser = serviceClient.findtById(id);
        serviceClient.HardDelet(id);
        return deletedUser;
    }
    //soft delet

    @PutMapping("/d/{id}")
    public ClientUser DeactiveClient(@PathVariable long id){
        return serviceClient.softDelet(id);
    }
    @PutMapping("/a/{id}")
    public ClientUser ActivateClient(@PathVariable long id){
        return  serviceClient.Activate(id);
    }

    @PutMapping("/edit/{id}")
    public ClientUser AlterClient(@PathVariable long id,@RequestBody ClientUser client){
        return serviceClient.UpdateClient(client,id);
    }
}
