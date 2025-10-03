package com.salonio.modules.business.api;

import com.salonio.modules.business.api.dto.category.business.BusinessCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryResponse;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BusinessCategoryApi {

    BusinessCategoryResponse createBusinessCategory(BusinessCategoryCreateRequest businessCategoryCreateRequest);

    BusinessCategoryResponse getBusinessCategory(UUID id);

    Page<BusinessCategoryResponse> getBusinessCategories(Pageable pageable);

    BusinessCategoryResponse updateBusinessCategory(UUID id, BusinessCategoryUpdateRequest businessCategoryUpdateRequest);

    void deleteBusinessCategory(UUID id);

}
