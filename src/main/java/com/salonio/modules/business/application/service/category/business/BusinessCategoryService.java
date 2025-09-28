package com.salonio.modules.business.application.service.category.business;

import com.salonio.modules.business.api.BusinessCategoryApi;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryResponse;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public class BusinessCategoryService implements BusinessCategoryApi {

    @Override
    public BusinessCategoryResponse createBusinessCategory(BusinessCategoryCreateRequest businessCategoryCreateRequest) {
        return null;
    }

    @Override
    public BusinessCategoryResponse getBusinessCategory(UUID id) {
        return null;
    }

    @Override
    public Page<BusinessCategoryResponse> getBusinessCategories(Pageable pageable) {
        return null;
    }

    @Override
    public BusinessCategoryResponse updateBusinessBooking(UUID id, BusinessCategoryUpdateRequest businessCategoryUpdateRequest) {
        return null;
    }

    @Override
    public void deleteBusinessCategory(UUID id) {

    }

}
