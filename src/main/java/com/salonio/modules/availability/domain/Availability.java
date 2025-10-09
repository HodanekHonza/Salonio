package com.salonio.modules.availability.domain;

import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Availability {

    @Id
    private UUID id;

    @Version
    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID staffId;

    private UUID businessId;

    private boolean availability;

    private UUID bookingId;

    private UUID clientId;

    public Availability(LocalDateTime startTime, LocalDateTime endTime, UUID  staffId,
                 UUID businessId, boolean availability, UUID bookingId, UUID clientId) {
        this.id = UUID.randomUUID();
        this.startTime = startTime;
        this.endTime = endTime;
        this.staffId = staffId;
        this.businessId = businessId;
        this.availability = availability;
        this.bookingId = bookingId;
        this.clientId = clientId;
    }


    public Availability confirm(UUID bookingId, UUID clientId) {
        if (!availability) throw new IllegalStateException("No available available for staff " + staffId);
        this.availability = false;
        this.bookingId = bookingId;
        this.clientId = clientId;
        return this;
    }

    public Availability updateEntity(UpdateAvailabilityRequest request) {
        this.setStartTime(request.startTime());
        this.setEndTime(request.endTime());
        this.setClientId(request.clientId());
        this.setStaffId(request.staffId());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Availability that)) return false;
        return availability == that.availability
                && Objects.equals(id, that.id)
                && Objects.equals(version, that.version)
                && Objects.equals(startTime, that.startTime)
                && Objects.equals(endTime, that.endTime)
                && Objects.equals(staffId, that.staffId)
                && Objects.equals(businessId, that.businessId)
                && Objects.equals(bookingId, that.bookingId)
                && Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                version,
                startTime,
                endTime,
                staffId,
                businessId,
                availability,
                bookingId,
                clientId
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("staffId", staffId)
                .append("businessId", businessId)
                .append("availability", availability)
                .append("bookingId", bookingId)
                .append("clientId", clientId)
                .toString();
    }

}
