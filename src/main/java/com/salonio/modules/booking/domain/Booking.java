package com.salonio.modules.booking.domain;

import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import com.salonio.modules.booking.domain.enums.BookingStatus;
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
public class Booking {

    private UUID id;

    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID clientId;

    private UUID staffId;

    private String serviceType;

    private BookingStatus status;

//    private String serviceNameSnapshot;
//    private BigDecimal priceSnapshot;
//    private Integer durationMinutes;
//
//    private BookingStatus status;
//    private PaymentStatus paymentStatus;
//
//    private String clientNotes;
//    private String staffNotes;
//    private String cancellationReason;
//
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//    private LocalDateTime confirmedAt;
//    private LocalDateTime cancelledAt;
//    private LocalDateTime reminderSentAt;
//    private LocalDateTime checkInAt;
//    private LocalDateTime checkOutAt;
//
//    private boolean noShow;

    public Booking(LocalDateTime startTime, LocalDateTime endTime, UUID clientId,
                   UUID staffId, String serviceType, BookingStatus status) {
        this.id = UUID.randomUUID();
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceType = serviceType;
        this.status = status;
    }

    public Booking(UUID id, Integer version, LocalDateTime startTime, LocalDateTime endTime,
                   UUID clientId, UUID staffId, String serviceType, BookingStatus status) {
        this.id = id;
        this.version = version;
        this.startTime = startTime;
        this.endTime = endTime;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceType = serviceType;
        this.status = status;
    }


    public Booking updateEntity(UpdateBookingRequest request) {
        if (request.startTime() != null) this.setStartTime(request.startTime());
        if (request.endTime() != null) this.setEndTime(request.endTime());
        if (request.clientId() != null) this.setClientId(request.clientId());
        if (request.staffId() != null) this.setStaffId(request.staffId());
        if (request.serviceType() != null && !request.serviceType().isBlank()) this.setServiceType(request.serviceType());
        if (request.status() != null) this.setStatus(request.status());
        return this;
    }

    public void confirm() {
        if (this.status == BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Booking already confirmed");
        }
        this.status = BookingStatus.CONFIRMED;
//        this.confirmedAt = LocalDateTime.now();
    }

    public void cancel(String reason) {
        if (this.status == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking already cancelled");
        }
        this.status = BookingStatus.CANCELLED;
//        this.cancellationReason = reason;
//        this.cancelledAt = LocalDateTime.now();
    }

    public void markNoShow() {
//        this.noShow = true;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Booking booking)) return false;
        return Objects.equals(id, booking.id)
                && Objects.equals(version, booking.version)
                && Objects.equals(startTime, booking.startTime)
                && Objects.equals(endTime, booking.endTime)
                && Objects.equals(clientId, booking.clientId)
                && Objects.equals(staffId, booking.staffId)
                && Objects.equals(serviceType, booking.serviceType)
                && status == booking.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                version,
                startTime,
                endTime,
                clientId,
                staffId,
                serviceType,
                status
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("clientId", clientId)
                .append("staffId", staffId)
                .append("serviceType", serviceType)
                .append("status", status)
                .toString();
    }

}
