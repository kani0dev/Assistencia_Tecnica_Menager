package Kani0dev.ATM.Controler.Output;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.Device.Device;

import java.util.List;

public record ClientResponse(
        Long id,
        String name,
        String telefone,
        Boolean isActive,
        List<Long> deviceIds
) {
    public static ClientResponse toResponse(ClientDTO client) {
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getTelefone(),
                client.getIsActive(),
                client.getDeviceList()
                        .stream()
                        .map(Device::getId)
                        .toList()
        );
    }
}
