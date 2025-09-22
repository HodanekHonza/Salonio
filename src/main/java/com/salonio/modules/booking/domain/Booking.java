package com.salonio.modules.booking.domain;

import com.salonio.modules.booking.domain.enums.BookingStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private UUID id;

    private Integer version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private UUID clientId;

    private UUID staffId;

    private String serviceType;

    private BookingStatus status;

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

    public Booking() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getStaffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking other)) return false;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public void confirm() {
        if (this.status == BookingStatus.CONFIRMED) throw new RuntimeException("Booking already confirmed");
        this.status = BookingStatus.CONFIRMED;
    }

}
