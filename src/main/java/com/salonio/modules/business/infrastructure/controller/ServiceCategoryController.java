package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.ServiceCategoryApi;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryResponse;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/service-category")
@RestController
public class ServiceCategoryController {

    private final ServiceCategoryApi serviceCategoryApi;

    @PostMapping
    ServiceCategoryResponse createServiceCategory(ServiceCategoryCreateRequest serviceCategoryCreateRequest) {
        return serviceCategoryApi.createServiceCategory(serviceCategoryCreateRequest);
    }

    @GetMapping("/{serviceCategoryId}")
    ServiceCategoryResponse getServiceCategory(@PathVariable UUID serviceCategoryId) {
        return serviceCategoryApi.getServiceCategory(serviceCategoryId);
    }

    @PutMapping("/{serviceCategoryId}")
    ServiceCategoryResponse updateServiceCategory(@PathVariable UUID serviceCategoryId,
                                                    @Valid @RequestBody ServiceCategoryUpdateRequest serviceCategoryUpdateRequest) {
        return serviceCategoryApi.updateServiceCategory(serviceCategoryId, serviceCategoryUpdateRequest);
    }

    @DeleteMapping("/{serviceCategoryId}")
    ResponseEntity<Void> deleteServiceCategory(@PathVariable UUID serviceCategoryId) {
        serviceCategoryApi.deleteServiceCategory(serviceCategoryId);
        return ResponseEntity.noContent().build();
    }

}
