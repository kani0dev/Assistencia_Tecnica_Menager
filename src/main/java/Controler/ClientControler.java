package Controler;

import Model.User.ClientUser;
import Service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientControler {
    private final ClienteService serviceClient;

    public ClientControler(ClienteService clienteService) {
        this.serviceClient = clienteService;
    }

    @GetMapping("/list")
    public List<ClientUser> ShowAllClients(){
        return serviceClient.ListClientUsers();
    }
    @GetMapping("/list/{id}")
    public ClientUser ShowClientById(@PathVariable long id){
        return serviceClient.findtById(id);
    }

    @PostMapping("/add")
    public void AddNewCLient(@RequestBody ClientUser client){
        serviceClient.SingUpClient(client);
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
        ClientUser deactivateduser = serviceClient.findtById(id);
        serviceClient.softDelet(id);
        return deactivateduser;
    }

    @PutMapping("/edit/{id}")
    public ClientUser AlterClient(@PathVariable long id,@RequestBody ClientUser client){
        return serviceClient.UpdateClient(client,id);
    }
}
