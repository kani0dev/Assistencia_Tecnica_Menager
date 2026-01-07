package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.User.ClientUser;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientUser map(ClientDTO clientDTO){
        ClientUser client = new ClientUser();

        client.setId(clientDTO.getId());
        client.setIsActive(clientDTO.getIsActive());
        client.setName(clientDTO.getName());
        client.setTelefone((clientDTO.getTelefone()));
        client.addDevice(clientDTO.getDeviceList().getFirst());
        client.setPassword(clientDTO.getPassword());

        return client;
    }

    public ClientDTO map(ClientUser client){
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setIsActive(client.getIsActive());
        clientDTO.setName(client.getName());
        clientDTO.setTelefone((client.getTelefone()));
        clientDTO.addDevice(client.getAllDevice().getFirst());

        clientDTO.setPassword(client.getPassword());

        return clientDTO;
    }
}
