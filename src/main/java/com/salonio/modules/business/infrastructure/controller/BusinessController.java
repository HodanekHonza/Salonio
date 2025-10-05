package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.*;
import com.salonio.modules.business.api.dto.business.BusinessCreateRequest;
import com.salonio.modules.business.api.dto.business.BusinessResponse;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryResponse;
import com.salonio.modules.business.api.dto.category.business.BusinessCategoryUpdateRequest;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryCreateRequest;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryResponse;
import com.salonio.modules.business.api.dto.category.service.ServiceCategoryUpdateRequest;
import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
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

    private static final String BUSINESS_ID_PREFIX = "/{businessId}";
    private static final String SERVICE_ID_PREFIX = "/{serviceId}";
    private static final String REVIEW_ID_PREFIX = "/{reviewId}";
    private static final String BUSINESS_CATEGORY_ID_PREFIX = "/{businessCategoryId}";
    private static final String SERVICE_CATEGORY_ID_PREFIX = "/{serviceCategoryId}";
    private static final String SERVICE_PREFIX = "/service";
    private static final String REVIEW_PREFIX = "/review";
    private static final String BUSINESS_CATEGORY_PREFIX = "/business-category";
    private static final String SERVICE_CATEGORY_PREFIX = "/service-category";

    private final BusinessApi businessApi;
    private final ServiceApi serviceApi;
    private final ReviewApi reviewApi;
    private final BusinessCategoryApi businessCategoryApi;
    private final ServiceCategoryApi serviceCategoryApi;

    // Business endpoints
    @PostMapping
    public BusinessResponse createBusiness(@Valid @RequestBody BusinessCreateRequest businessCreateRequest) {
        return businessApi.createBusiness(businessCreateRequest);
    }

    @GetMapping(BUSINESS_ID_PREFIX)
    public BusinessResponse getBusiness(@PathVariable UUID businessId) {
        return businessApi.getBusiness(businessId);
    }

    @GetMapping("/list/{businessType}")
    public Page<BusinessResponse> listBusinesses(@PathVariable String businessType, Pageable pageable) {
        return businessApi.listBusinessesByBusinessType(businessType, pageable);
    }

    @PutMapping(BUSINESS_ID_PREFIX)
    public BusinessResponse updateBusiness(@PathVariable UUID businessId,
                                           @Valid @RequestBody BusinessUpdateRequest businessUpdateRequest) {
        return businessApi.updateBusiness(businessId, businessUpdateRequest);
    }

    @DeleteMapping(BUSINESS_ID_PREFIX)
    public ResponseEntity<Void> deleteBusiness(@PathVariable UUID businessId) {
        businessApi.deleteBusiness(businessId);
        return ResponseEntity.noContent().build();
    }

    // Services endpoints
    @GetMapping(BUSINESS_ID_PREFIX + "/services")
    public Page<ServiceResponse> listServicesByBusiness(@PathVariable UUID businessId,
                                                        Pageable pageable) {
        return serviceApi.listServicesByBusiness(businessId, pageable);
    }

    @PostMapping(BUSINESS_ID_PREFIX + SERVICE_PREFIX)
    public ServiceResponse createService(@PathVariable UUID businessId,
                                         @Valid @RequestBody ServiceCreateRequest serviceCreateRequest) {
        return serviceApi.createService(businessId, serviceCreateRequest);
    }

    @GetMapping(BUSINESS_ID_PREFIX + SERVICE_PREFIX + SERVICE_ID_PREFIX)
    public ServiceResponse getService(@PathVariable UUID businessId,
                                      @PathVariable UUID serviceId) {
        return serviceApi.getService(businessId, serviceId);
    }

    @PutMapping(BUSINESS_ID_PREFIX + SERVICE_PREFIX + SERVICE_ID_PREFIX)
    public ServiceResponse updateService(@PathVariable UUID businessId,
                                         @PathVariable UUID serviceId,
                                         @Valid @RequestBody ServiceUpdateRequest serviceUpdateRequest) {
        return serviceApi.updateService(serviceId, serviceUpdateRequest);
    }

    @DeleteMapping(BUSINESS_ID_PREFIX + SERVICE_PREFIX + SERVICE_ID_PREFIX)
    public ResponseEntity<Void> deleteService(@PathVariable UUID businessId,
                                              @PathVariable UUID serviceId) {
        serviceApi.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }

    // Reviews endpoints
    @GetMapping(BUSINESS_ID_PREFIX + "/reviews")
    public Page<ReviewResponse> listReviewsByBusiness(@PathVariable UUID businessId,
                                                      Pageable pageable) {
        return reviewApi.listReviewsByBusiness(businessId, pageable);
    }

    @PostMapping(BUSINESS_ID_PREFIX + REVIEW_PREFIX)
    public ReviewResponse createReview(@PathVariable UUID businessId,
                                       @Valid @RequestBody ReviewCreateRequest reviewCreateRequest) {
        return reviewApi.createReview(reviewCreateRequest);
    }

    @GetMapping(BUSINESS_ID_PREFIX + REVIEW_PREFIX + REVIEW_ID_PREFIX)
    public ReviewResponse getReview(@PathVariable UUID businessId,
                                    @PathVariable UUID reviewId) {
        return reviewApi.getReview(businessId, reviewId);
    }

    @PutMapping(BUSINESS_ID_PREFIX + REVIEW_PREFIX + REVIEW_ID_PREFIX)
    public ReviewResponse updateReview(@PathVariable UUID businessId,
                                       @PathVariable UUID reviewId,
                                       @Valid @RequestBody ReviewUpdateRequest reviewUpdateRequest) {
        return reviewApi.updateReview(reviewId, reviewUpdateRequest);
    }

    @DeleteMapping(BUSINESS_ID_PREFIX + REVIEW_PREFIX + REVIEW_ID_PREFIX)
    public ResponseEntity<Void> deleteReview(@PathVariable UUID businessId,
                                             @PathVariable UUID reviewId) {
        reviewApi.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

    // Business Category endpoints
    @PostMapping(BUSINESS_ID_PREFIX + BUSINESS_CATEGORY_PREFIX)
    public BusinessCategoryResponse createBusinessCategory(@PathVariable UUID businessId,
                                                           @Valid @RequestBody BusinessCategoryCreateRequest request) {
        return businessCategoryApi.createBusinessCategory(request);
    }

    @GetMapping(BUSINESS_ID_PREFIX + BUSINESS_CATEGORY_PREFIX + BUSINESS_CATEGORY_ID_PREFIX)
    public BusinessCategoryResponse getBusinessCategory(@PathVariable UUID businessId,
                                                        @PathVariable UUID businessCategoryId) {
        return businessCategoryApi.getBusinessCategory(businessId, businessCategoryId);
    }

    @PutMapping(BUSINESS_ID_PREFIX + BUSINESS_CATEGORY_PREFIX + BUSINESS_CATEGORY_ID_PREFIX)
    public BusinessCategoryResponse updateBusinessCategory(@PathVariable UUID businessId,
                                                           @PathVariable UUID businessCategoryId,
                                                           @Valid @RequestBody BusinessCategoryUpdateRequest request) {
        return businessCategoryApi.updateBusinessCategory(businessCategoryId, request);
    }

    @DeleteMapping(BUSINESS_ID_PREFIX + BUSINESS_CATEGORY_PREFIX + BUSINESS_CATEGORY_ID_PREFIX)
    public ResponseEntity<Void> deleteBusinessCategory(@PathVariable UUID businessId,
                                                       @PathVariable UUID businessCategoryId) {
        businessCategoryApi.deleteBusinessCategory(businessCategoryId);
        return ResponseEntity.noContent().build();
    }

    // Service Category endpoints
    @PostMapping(BUSINESS_ID_PREFIX + SERVICE_CATEGORY_PREFIX)
    public ServiceCategoryResponse createServiceCategory(@PathVariable UUID businessId,
                                                         @Valid @RequestBody ServiceCategoryCreateRequest request) {
        return serviceCategoryApi.createServiceCategory(request);
    }

    @GetMapping(BUSINESS_ID_PREFIX + SERVICE_CATEGORY_PREFIX + SERVICE_CATEGORY_ID_PREFIX)
    public ServiceCategoryResponse getServiceCategory(@PathVariable UUID businessId,
                                                      @PathVariable UUID serviceCategoryId) {
        return serviceCategoryApi.getServiceCategory(businessId, serviceCategoryId);
    }

    @PutMapping(BUSINESS_ID_PREFIX + SERVICE_CATEGORY_PREFIX + SERVICE_CATEGORY_ID_PREFIX)
    public ServiceCategoryResponse updateServiceCategory(@PathVariable UUID businessId,
                                                         @PathVariable UUID serviceCategoryId,
                                                         @Valid @RequestBody ServiceCategoryUpdateRequest request) {
        return serviceCategoryApi.updateServiceCategory(serviceCategoryId, request);
    }

    @DeleteMapping(BUSINESS_ID_PREFIX + SERVICE_CATEGORY_PREFIX + SERVICE_CATEGORY_ID_PREFIX)
    public ResponseEntity<Void> deleteServiceCategory(@PathVariable UUID businessId,
                                                      @PathVariable UUID serviceCategoryId) {
        serviceCategoryApi.deleteServiceCategory(serviceCategoryId);
        return ResponseEntity.noContent().build();
    }

}
