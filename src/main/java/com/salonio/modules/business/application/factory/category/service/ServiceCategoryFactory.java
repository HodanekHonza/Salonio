package com.salonio.modules.business.application.factory.category.service;

import com.salonio.modules.business.api.dto.category.service.ServiceCategoryCreateRequest;
import com.salonio.modules.business.domain.ServiceCategory;

public class ServiceCategoryFactory {

    private ServiceCategoryFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ServiceCategory createServiceCategory(ServiceCategoryCreateRequest dto) {
        return new ServiceCategory(
                dto.name(),
                dto.description(),
                dto.active(),
                dto.createdAt(),
                dto.updatedAt(),
                dto.icon(),
                dto.colorCode()
        );
    }

}