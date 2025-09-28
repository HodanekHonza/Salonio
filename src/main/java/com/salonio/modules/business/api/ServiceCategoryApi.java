package com.salonio.modules.business.api;

import com.salonio.modules.business.api.dto.category.service.ServiceCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryResponse;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ServiceCategoryApi {

    ServiceCategoryResponse createServiceCategory(ServiceCategoryCreateRequest serviceCategoryCreateRequest);

    ServiceCategoryResponse getServiceCategory(UUID id);

    Page<ServiceCategoryResponse> getServiceCategories(UUID businessId, Pageable pageable);

    ServiceCategoryResponse updateServiceCategory(UUID id, ServiceCategoryUpdateRequest serviceCategoryUpdateRequest);

    void deleteService(UUID id);

}
