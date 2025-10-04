package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import com.salonio.modules.business.application.service.service.ServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/service")
@RestController
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    ServiceResponse createService(@Valid @RequestBody ServiceCreateRequest serviceCreateRequest) {
        return serviceService.createService(serviceCreateRequest);
    }

    @GetMapping("/{serviceId}")
    ServiceResponse getService(@PathVariable UUID serviceId) {
        return serviceService.getService(serviceId);
    }

    @PutMapping("/{serviceId}")
    void updateService(@PathVariable UUID serviceId,
                        ServiceUpdateRequest serviceUpdateRequest) {
        serviceService.updateService(serviceId, serviceUpdateRequest);
    }

    @DeleteMapping("/{serviceId}")
    ResponseEntity<Void> deleteService(@PathVariable UUID serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }

}
