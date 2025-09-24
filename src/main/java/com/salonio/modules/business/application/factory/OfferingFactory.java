package com.salonio.modules.business.application.factory;

import com.salonio.modules.business.api.dto.CreateOfferingRequest;
import com.salonio.modules.business.domain.Offering;

public final class OfferingFactory {

    private OfferingFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Offering createOffering(CreateOfferingRequest createOfferingRequest) {
        return new Offering(
                createOfferingRequest.textForm(),
                createOfferingRequest.businessId()
        );
    }

}
