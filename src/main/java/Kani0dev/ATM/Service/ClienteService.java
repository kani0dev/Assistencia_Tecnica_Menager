package Kani0dev.ATM.Service;

import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.ClientRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
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
        clientUser.setIsActive(true);
        return  ClientRepository.save(clientUser);
    }


    //delet
    public void HardDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientRepository.deleteById(id);
        }
    }

    //deactivat and activate
    public ClientUser softDelet(long id){
        Optional<ClientUser> clientToDeactivate = ClientRepository.findById(id);
        if(clientToDeactivate.isPresent()){
            ClientUser client = clientToDeactivate.get();

            if(client.getIsActive() == true ){
                client.setIsActive(false);
            }
            if(client.getIsActive() == false){
                client.setIsActive(true);
            }

            ClientRepository.save(client);
            return  client;
        }
        return null;
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


    // devices operations

}
