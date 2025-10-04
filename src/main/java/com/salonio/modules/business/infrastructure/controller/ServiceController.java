package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.ServiceApi;
import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/service")
@RestController
public class ServiceController {

    private final ServiceApi serviceApi;

    @PostMapping
    ServiceResponse createService(@Valid @RequestBody ServiceCreateRequest serviceCreateRequest) {
        return serviceApi.createService(serviceCreateRequest);
    }

    @GetMapping("/{serviceId}")
    ServiceResponse getService(@PathVariable UUID serviceId) {
        return serviceApi.getService(serviceId);
    }

    @PutMapping("/{serviceId}")
    void updateService(@PathVariable UUID serviceId,
                        ServiceUpdateRequest serviceUpdateRequest) {
        serviceApi.updateService(serviceId, serviceUpdateRequest);
    }

    @DeleteMapping("/{serviceId}")
    ResponseEntity<Void> deleteService(@PathVariable UUID serviceId) {
        serviceApi.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }

}
