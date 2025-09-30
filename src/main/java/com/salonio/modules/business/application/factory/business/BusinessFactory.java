package com.salonio.modules.business.application.factory.business;

import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.domain.Business;

public final class BusinessFactory {

    private BusinessFactory() {
        throw new UnsupportedOperationException("Utility class");
    }


    public static Business createBusiness(BusinessCreateRequest dto) {
        return new Business(
                dto.businessName(),
                dto.creationDate(),
                dto.address(),
                dto.email(),
                dto.phoneNumber(),
                dto.websiteUrl(),
                dto.taxId(),
                dto.city(),
                dto.postalCode(),
                dto.country(),
                dto.latitude(),
                dto.longitude(),
                dto.businessType(),
                dto.openingTime(),
                dto.closingTime(),
                dto.isActive(),
                dto.rating(),
                dto.numberOfReviews(),
                dto.totalBookings(),
                dto.logoUrl(),
                dto.description(),
                dto.currency()
        );
    }

}