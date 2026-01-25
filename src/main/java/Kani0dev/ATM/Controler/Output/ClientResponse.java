package Kani0dev.ATM.Controler.Output;

import Kani0dev.ATM.DTO.ClientDTO;
import Kani0dev.ATM.Model.Device.Device;
import lombok.Builder;

import java.util.List;

@Builder
public record ClientResponse(
        String name,
        String telefone,
        Boolean isActive,
        List<Long> deviceIds
) {
    public static ClientResponse toResponse(ClientDTO client) {
        return  ClientResponse.builder()
                .name(client.getName())
                .telefone(client.getTelefone())
                .isActive(client.getIsActive())
                .deviceIds(client.getDeviceList().stream()
                        .map(Device::getId)
                        .toList()
                )
                .build();
    }
}
