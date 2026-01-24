package Kani0dev.ATM.Mapper;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.Device.Device;
import Kani0dev.ATM.Model.User.ClientUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientMapper {

    public ClientUser toEntity(ClientDTO dto) {
        ClientUser client = ClientUser.builder()
                .id(dto.getId())
                .name(dto.getName())
                .password(dto.getPassword())
                .telefone(dto.getTelefone())
                .isActive(dto.getIsActive())
                .build();

        List<Device> devices = Optional.ofNullable(dto.getDeviceList())
                .orElseGet(ArrayList::new)
                .stream()
                .toList();

        client.setDeviceList(devices);
        return  client;
    }

    // Converte Entidade (Objetos) -> DTO (IDs)
    public ClientDTO toDTO(ClientUser entity) {
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setTelefone(entity.getTelefone());
        dto.setIsActive(entity.getIsActive());
        dto.setPassword(entity.getPassword());

        // Mapeia a lista de objetos Device para uma lista de Long (IDs)
        if (entity.getAllDevice() != null) {
            List<Device> ids = entity.getAllDevice().stream().toList();
            dto.addDevices(ids);
        }

        return dto;
    }
}