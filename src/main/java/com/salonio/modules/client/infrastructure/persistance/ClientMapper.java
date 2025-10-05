package com.salonio.modules.client.infrastructure.persistance;

import com.salonio.modules.client.api.dto.ClientResponse;
import com.salonio.modules.client.domain.Client;

public final class ClientMapper {

    private ClientMapper() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static ClientResponse toResponse(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientResponse(
                client.getId(),
                client.getVersion(),
                client.getUserId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getDateOfBirth(),
                client.getGender(),
                client.getAddressLine(),
                client.getCity(),
                client.getPostalCode(),
                client.getCountry(),
                client.getActive(),
                client.getNotes(),
                client.getLoyaltyPoints(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }

    public static Client toDomain(ClientJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Client(
                entity.getId(),
                entity.getVersion(),
                entity.getUserId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getDateOfBirth(),
                entity.getGender(),
                entity.getAddressLine(),
                entity.getCity(),
                entity.getPostalCode(),
                entity.getCountry(),
                entity.getIsActive(),
                entity.getNotes(),
                entity.getLoyaltyPoints(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ClientJpaEntity fromDomain(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientJpaEntity(
                client.getId(),
                client.getVersion(),
                client.getUserId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getDateOfBirth(),
                client.getGender(),
                client.getAddressLine(),
                client.getCity(),
                client.getPostalCode(),
                client.getCountry(),
                client.getActive(),
                client.getNotes(),
                client.getLoyaltyPoints(),
                client.getCreatedAt(),
                client.getUpdatedAt()
        );
    }
}
