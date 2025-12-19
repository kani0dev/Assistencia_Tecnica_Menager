package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.ClientRepo;
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
    public List<ClientUser> ListClientUsers(){
        return ClientRepository.findAll();
    }

    //get by id
    public ClientUser findtById(long id){
        Optional<ClientUser> thisClientById = ClientRepository.findById(id);
        return thisClientById.orElse(null);
    }

    //add
    public ClientUser SingUpClient(ClientUser clientUser){
        if(clientUser == null){
            return clientUser;
        }
        return  ClientRepository.save(clientUser);
    }


    //delet
    public void HardDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientRepository.deleteById(id);
        }
    }
    public void softDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientUser thisClientById = findtById(id);
            thisClientById.setIsActive(false);
        }
    }

    //update
    public ClientUser UpdateClient(ClientUser clientUser, long id){
        Optional<ClientUser> thisExistentCLient = ClientRepository.findById(id);

        if(thisExistentCLient.isPresent()){
            clientUser.setId(id);
            ClientRepository.save(clientUser);
            return clientUser;
        }
        return null;
    }
}
