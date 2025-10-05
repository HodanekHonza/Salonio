package com.salonio.modules.staff.api;

import com.salonio.modules.staff.api.dto.StaffResponse;
import com.salonio.modules.staff.api.dto.StaffUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StaffApi {
    StaffResponse getStaff(UUID clientId);

    Page<StaffResponse> findStaffByBusiness(UUID businessId, Pageable pageable);

    StaffResponse updateStaff(UUID clientId, StaffUpdateRequest clientUpdateRequest);

    void deleteStaff(UUID clientId);
}
