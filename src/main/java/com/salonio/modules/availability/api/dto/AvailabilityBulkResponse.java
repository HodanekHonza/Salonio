package com.salonio.modules.availability.api.dto;

import java.util.List;

public record AvailabilityBulkResponse(
        int sizeOfBulk,
        List<AvailabilityResponse> availabilityResponseList
) {
}
