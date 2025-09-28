package com.salonio.modules.business.application.service.business;

import com.salonio.modules.business.api.BusinessApi;
import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.domain.Business;
import com.salonio.modules.business.infrastructure.persistence.business.BusinessRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BusinessService implements BusinessApi {

    @Override
    public BusinessResponse createBusiness(BusinessCreateRequest businessCreateRequest) {
        return null;
    }

    @Override
    public BusinessResponse getBooking(UUID id) {
        return null;
    }

    @Override
    public Page<ReviewResponse> getBusinesses(String category, Pageable pageable) {
        return null;
    }

    @Override
    public BusinessResponse updateBooking(UUID id, BusinessUpdateRequest businessUpdateRequest) {
        return null;
    }

    @Override
    public void deleteBusiness(UUID id) {

    }

}
