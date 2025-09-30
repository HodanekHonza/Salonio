package com.salonio.modules.business.api;

import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface BusinessApi {

    BusinessResponse createBusiness(BusinessCreateRequest businessCreateRequest);

    BusinessResponse getBusiness(UUID id);

    Page<ReviewResponse> getBusinesses(String category, Pageable pageable);

    BusinessResponse updateBusiness(UUID id, BusinessUpdateRequest businessUpdateRequest);

    void deleteBusiness(UUID id);

}
