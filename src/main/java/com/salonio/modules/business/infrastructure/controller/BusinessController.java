package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.BusinessApi;
import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessApi businessApi;

    BusinessController(BusinessApi businessApi) {
        this.businessApi = businessApi;
    }

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

}
