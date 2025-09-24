package com.salonio.modules.business.infrastructure.persistence;

import com.salonio.modules.business.api.dto.OfferingResponse;
import com.salonio.modules.business.domain.Offering;

public final class OfferingMapper {

    private OfferingMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void updateEntity(final OfferingResponse offeringResponse, Offering offering) {
        offering.setTextForm(offeringResponse.textForm());
        offering.setBusinessId(offeringResponse.businessId());
    }

    public static OfferingResponse toResponse(final Offering offering) {
        if (offering == null) {
            return null;
        }
        return new OfferingResponse(
                offering.getTextForm(),
                offering.getBusinessId()
        );
    }

}
