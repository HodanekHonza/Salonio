package com.salonio.offering.internal;

import com.salonio.offering.dto.CreateOfferingRequest;

final class OfferingFactory {

    private OfferingFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    static Offering createOffering(CreateOfferingRequest createOfferingRequest) {
        return new Offering(
                createOfferingRequest.textForm(),
                createOfferingRequest.businessId()
        );
    }

}
