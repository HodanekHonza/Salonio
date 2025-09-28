package com.salonio.modules.business.api;

import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ServiceApi {

    ServiceResponse createReview(ServiceCreateRequest serviceCreateRequest);

    ServiceResponse getService(UUID id);

    Page<ServiceResponse> getServices(UUID businessId, Pageable pageable);

    ServiceResponse updateService(UUID id, ServiceUpdateRequest serviceUpdateRequest);

    void deleteService(UUID id);

}
