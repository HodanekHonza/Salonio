package com.salonio.offering.internal;

import com.salonio.offering.dto.OfferingResponse;

final class OfferingMapper {

    private OfferingMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    static void updateEntity(final OfferingResponse offeringResponse, Offering offering) {
        offering.setTextForm(offeringResponse.textForm());
        offering.setBusinessId(offeringResponse.businessId());
    }

    static OfferingResponse toResponse(final Offering offering) {
        if (offering == null) {
            return null;
        }
        return new OfferingResponse(
                offering.getTextForm(),
                offering.getBusinessId()
        );
    }

}
