package Kani0dev.ATM.Service;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Mapper.ClientMapper;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import Kani0dev.ATM.Repository.ClientRepo;
import Kani0dev.ATM.Repository.DeviceRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ClienteService {
    private final ClientRepo ClientRepository;
    private final DeviceRepo devicerepository;
    private final DeviceService deviceService;
    private final ClientMapper clientMapper;

    public ClienteService(ClientRepo clientRepository, DeviceRepo devicerepository, DeviceService deviceService, ClientMapper clientMapper) {
        ClientRepository = clientRepository;
        this.devicerepository = devicerepository;
        this.deviceService = deviceService;
        this.clientMapper = clientMapper;
    }

    //get all
    public List<ClientDTO> ListClientUsers(){
            List<ClientUser> clients = ClientRepository.findAll();

            return clients.stream().map(clientMapper :: map).toList();
    }

    //get by id
    public ClientUser findtById(long id){
        Optional<ClientUser> thisClientById = ClientRepository.findById(id);
        return thisClientById.orElse(null);
    }

    //add
    public ClientDTO SingUpClient(ClientDTO clientUser){
        ClientUser client = clientMapper.map(clientUser);
        ClientRepository.save(client);
        return  clientUser;
    }


    //delet
    public void HardDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientRepository.deleteById(id);
        }
    }

    //deactivat and activate
    public ClientUser activate(long id,boolean bool) {
        Optional<ClientUser> clientToDeactivate = ClientRepository.findById(id);
        ClientUser client = clientToDeactivate.get();

        client.setIsActive(bool);
        ClientRepository.save(client);
        return client;
    }
    public ClientUser deactivate(long id,boolean bool){
        return activate(id,bool);
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
    public ClientUser addExistentDevice(long clientId, long deviceId) {
        ClientUser client = findtById(clientId);
        Device device = deviceService.findDeviceByid(deviceId);

        client.addDevice(device);

        ClientRepository.save(client);
        devicerepository.save(device);

        return client;
    }

    public ClientUser removeADevice(long client_id,long device_id){
        ClientUser client  = findtById(client_id);
        Device device = deviceService.findDeviceByid(device_id);

        client.rmDevice(device);

        ClientRepository.save(client);
        devicerepository.save(device);

        return client;
    }

    public List<Device> seeAllDevices(long client_id){
        ClientUser client  = findtById(client_id);

        return  client.getAllDevice();
    }


}
