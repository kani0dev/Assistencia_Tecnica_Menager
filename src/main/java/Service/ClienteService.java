package Service;

import Model.User.ClientUser;
import Repository.ClientRepo;
import ch.qos.logback.core.net.server.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    //classe q é responsavel por persistencia dos dados
    private final ClientRepo ClientRepository;

    public ClienteService(ClientRepo clientRepository) {
        ClientRepository = clientRepository;
    }

    //get all
    List<ClientUser> ListClientUsers(){
        return ClientRepository.findAll();
    }

    //get by id
    ClientUser findtById(long id){
        Optional<ClientUser> thisClientById = ClientRepository.findById(id);
        return thisClientById.orElse(null);

    }

    //add
    ClientUser SingUpClient(ClientUser clientUser){
        return  ClientRepository.save(clientUser);
    }


    //delet
    void HardDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientRepository.deleteById(id);
        }
    }
    void softDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientUser thisClientById = findtById(id);
            thisClientById.setIsActive(false);
        }
    }

    //update
    ClientUser UpdateClient(ClientUser clientUser, long id){
        Optional<ClientUser> thisExistentCLient = ClientRepository.findById(id);

        if(thisExistentCLient.isPresent()){
            clientUser.setId(id);
            ClientRepository.save(clientUser);
            return clientUser;
        }
        return null;
    }
}
