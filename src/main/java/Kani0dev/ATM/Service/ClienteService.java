package Kani0dev.ATM.Service;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.DTO.DeviceDTO;
import Kani0dev.ATM.Mapper.ClientMapper;
import Kani0dev.ATM.Mapper.DeviceMapper;
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

            return clients.stream().map(clientMapper :: toDTO).toList();
    }

    //get by id
    public ClientUser findtById(long id){
        Optional<ClientUser> thisClientById = ClientRepository.findById(id);
        return thisClientById.orElse(null);
    }

    //add
    public ClientDTO SingUpClient(ClientDTO clientUser){
        ClientUser client = clientMapper.toEntity(clientUser);
        ClientRepository.save(client);
        return  clientUser;
    }


    //delet
    public void HardDelet(long id){
        if(ClientRepository.existsById(id)){
            ClientRepository.deleteById(id);
        }
    }

    //set active status (activate/deactivate)
    public ClientUser setActiveStatus(long id, boolean active) {
        ClientUser client = ClientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.setIsActive(active);
        return ClientRepository.save(client);
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
        DeviceDTO dto = deviceService.findDeviceById(deviceId);


        Device device = DeviceMapper.toEntity(dto,client);
        client.addDevice(device);

        ClientRepository.save(client);
        devicerepository.save(device);

        return client;
    }

    public ClientUser removeADevice(long client_id,long device_id){
        ClientUser client  = findtById(client_id);
        DeviceDTO dto = deviceService.findDeviceById(device_id);

        Device device = DeviceMapper.toEntity(dto,client);
        client.rmDevice(device);

        devicerepository.delete(device);
        return ClientRepository.save(client);
    }

    public List<Device> seeAllDevices(long client_id){
        ClientUser client  = findtById(client_id);

        return  client.getAllDevice();
    }


}
