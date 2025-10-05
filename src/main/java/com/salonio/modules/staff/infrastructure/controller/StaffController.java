package com.salonio.modules.staff.infrastructure.controller;

import com.salonio.modules.staff.api.StaffApi;
import com.salonio.modules.staff.api.dto.StaffResponse;
import com.salonio.modules.staff.api.dto.StaffUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffApi staffApi;

    @GetMapping("/{staffId}")
    StaffResponse getStaff(@PathVariable UUID staffId) {
        return staffApi.getStaff(staffId);
    }

    @GetMapping("/business/{businessId}")
    public Page<StaffResponse> listStaffByBusiness(@PathVariable UUID businessId,
                                                   Pageable pageable) {
        return staffApi.findStaffByBusiness(businessId, pageable);
    }

    @PutMapping("/{staffId}")
    StaffResponse updateStaff(@PathVariable UUID staffId,
                                @Valid @RequestBody StaffUpdateRequest staffUpdateRequest) {
        return staffApi.updateStaff(staffId, staffUpdateRequest);
    }

    @DeleteMapping("/{staffId}")
    ResponseEntity<Void> deleteStaff(@PathVariable UUID staffId) {
        staffApi.deleteStaff(staffId);
        return ResponseEntity.noContent().build();
    }

}
