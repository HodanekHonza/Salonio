//package com.salonio.modules.booking.infrastructure.persistence;
//
//import com.salonio.modules.booking.domain.Booking;
//import com.salonio.modules.booking.domain.enums.BookingStatus;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//import org.springframework.data.annotation.Id;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//// BookingJpaEntity.java
//@Entity
//@Table(name = "booking")
//class BookingJpaEntity {
//    @Id
//    private UUID id;
//
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private UUID clientId;
//    private UUID staffId;
//    private String serviceType;
//    private BookingStatus status;
//
//
//    public Booking toDomain() {
//        return new Booking(startTime, endTime, clientId, staffId, serviceType, status);
//    }
//
//    public static BookingJpaEntity fromDomain(Booking booking) {
//        BookingJpaEntity entity = new BookingJpaEntity();
//        entity.id = booking.getId();
//        entity.startTime = booking.getStartTime();
//        entity.endTime = booking.getEndTime();
//        entity.clientId = booking.getClientId();
//        entity.staffId = booking.getStaffId();
//        entity.serviceType = booking.getServiceType();
//        entity.status = booking.getStatus();
//        return entity;
//    }
//}
//
