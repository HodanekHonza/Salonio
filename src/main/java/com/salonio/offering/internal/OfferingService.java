package com.salonio.offering.internal;

import com.salonio.offering.OfferingApi;
import com.salonio.offering.dto.CreateOfferingRequest;
import com.salonio.offering.dto.OfferingResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferingService implements OfferingApi {

    private final OfferingRepository offeringRepository;
    private final ApplicationEventPublisher publisher;

    OfferingService(OfferingRepository offeringRepository, ApplicationEventPublisher publisher) {
        this.offeringRepository = offeringRepository;
        this.publisher = publisher;
    }

    OfferingResponse createOffering(CreateOfferingRequest createOfferingRequest) {
        final var newOffering = OfferingFactory.createOffering(createOfferingRequest);
        try {

            offeringRepository.save(newOffering);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Publish event
        publisher.publishEvent(new Object());

        // Return Response Dto
        return OfferingMapper.toResponse(newOffering);
    }

    OfferingResponse getOffering(UUID offeringId) {
        final Offering foundBooking = offeringRepository.findById(offeringId)
                .orElseThrow();
        return OfferingMapper.toResponse(foundBooking);
    }

    void updateOffering() {

    }

    void deleteOffering(UUID offeringId) {
        if (!offeringRepository.existsById(offeringId)) {
            throw new EntityNotFoundException("Offering not found: " + offeringId);
        }
        try {
            offeringRepository.deleteById(offeringId);
        } catch (EmptyResultDataAccessException e) {

        }
        // publish deletion event
    }

}
