//package com.salonio.booking.internal;
//
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.event.EventListener;
//import org.springframework.dao.OptimisticLockingFailureException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class BookingEventListener {
//
//    private final ApplicationEventPublisher publisher;
//    private final BookingRepository bookingRepository;
//
//    BookingEventListener(ApplicationEventPublisher publisher, BookingRepository bookingRepository) {
//        this.publisher = publisher;
//        this.bookingRepository = bookingRepository;
//    }
//
//    @EventListener
//    void saveBookingResult() {
//        Booking savedBooking;
//        // refactor try
//        try {
////            savedBooking = bookingRepository.save(newBooking);
//        } catch (OptimisticLockingFailureException e) {
//            throw new RuntimeException();
//        }
//    }
//}
