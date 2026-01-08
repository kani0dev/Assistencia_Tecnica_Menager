package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.User.ClientUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientMapper {

    // Converte DTO (IDs) -> Entidade (Objetos)
    public ClientUser map(ClientDTO dto) {
        ClientUser client = new ClientUser();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setPassword(dto.getPassword());
        client.setTelefone(dto.getTelefone());
        client.setIsActive(dto.getIsActive());

        return client;
    }

    // Converte Entidade (Objetos) -> DTO (IDs)
    public ClientDTO map(ClientUser entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTelefone(entity.getTelefone());
        dto.setIsActive(entity.getIsActive());
        dto.setPassword(entity.getPassword());

        // Mapeia a lista de objetos Device para uma lista de Long (IDs)
        if (entity.getAllDevice() != null) {
            List<Long> ids = entity.getAllDevice().stream()
                    .map(device -> device.getId())
                    .toList();
            dto.addDevices(ids);
        }

        return dto;
    }
}