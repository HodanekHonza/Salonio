package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.BusinessCategoryApi;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryResponse;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/business-category")
@RestController
public class BusinessCategoryController {
    private final BusinessCategoryApi businessCategoryApi;

    @PostMapping
    BusinessCategoryResponse createBusinessCategory(BusinessCategoryCreateRequest businessCategoryCreateRequest) {
        return businessCategoryApi.createBusinessCategory(businessCategoryCreateRequest);
    }

    @GetMapping("/{businessCategoryId}")
    BusinessCategoryResponse getBusinessCategory(@PathVariable UUID businessCategoryId) {
        return businessCategoryApi.getBusinessCategory(businessCategoryId);
    }

    @PutMapping("/{businessCategoryId}")
    BusinessCategoryResponse updateBusinessCategory(@PathVariable UUID businessCategoryId,
                                    @Valid @RequestBody BusinessCategoryUpdateRequest businessCategoryUpdateRequest) {
        return businessCategoryApi.updateBusinessCategory(businessCategoryId, businessCategoryUpdateRequest);
    }

    @DeleteMapping("/{businessCategoryId}")
    ResponseEntity<Void> deleteBusinessCategory(@PathVariable UUID businessCategoryId) {
        businessCategoryApi.deleteBusinessCategory(businessCategoryId);
        return ResponseEntity.noContent().build();
    }

}
