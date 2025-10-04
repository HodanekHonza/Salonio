package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.BusinessApi;
import com.salonio.modules.business.api.ReviewApi;
import com.salonio.modules.business.api.ServiceApi;
import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/business")
@RestController
public class BusinessController {

    private final BusinessApi businessApi;
    private final ServiceApi serviceApi;
    private final ReviewApi reviewApi;

    @PostMapping
    BusinessResponse createBusiness(BusinessCreateRequest businessCreateRequest) {
        return businessApi.createBusiness(businessCreateRequest);
    }

    @GetMapping("/{businessId}")
    BusinessResponse getBusiness(@PathVariable UUID businessId) {
        return businessApi.getBusiness(businessId);
    }

    @PutMapping("/{businessId}")
    BusinessResponse updateBusiness(@PathVariable UUID businessId,
                                  @Valid @RequestBody BusinessUpdateRequest businessUpdateRequest) {
        return businessApi.updateBusiness(businessId, businessUpdateRequest);
    }

    @DeleteMapping("/{businessId}")
    ResponseEntity<Void> deleteBusiness(@PathVariable UUID businessId) {
        businessApi.deleteBusiness(businessId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{businessId}/services")
    Page<ServiceResponse> listServicesByBusiness(@PathVariable UUID businessId,
                                                 Pageable pageable) {
        return serviceApi.listServicesByBusiness(businessId, pageable);
    }

    @GetMapping("{businessId}/reviews")
    Page<ReviewResponse> listReviewsByBusiness(@PathVariable UUID businessId,
                                                Pageable pageable) {
        return reviewApi.listReviewsByBusiness(businessId, pageable);
    }



}
