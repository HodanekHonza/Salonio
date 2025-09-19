package com.salonio.modules.common.util;

import com.salonio.modules.availability.domain.Availability;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.booking.domain.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import java.util.UUID;

/*
    TODO Rewrite with Spring retry
 */
public final class RetryUtils {

    private RetryUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(RetryUtils.class);

    public static AvailabilitySlotConfirmedEvent retryMechanism(Availability slot, UUID bookingId, UUID clientId) {
        for (int i = 0; true; i++) {
            try {
                return slot.confirm(bookingId, clientId);
            } catch (OptimisticLockingFailureException e) {
                if (i == 2) throw e;
                logger.warn("Retrying due to optimistic lock failure...");
            }
        }
    }

    public static void retryMechanism(Booking slot) {
        for (int i = 0; true; i++) {
            try {
                slot.confirm();
                break;
            } catch (OptimisticLockingFailureException e) {
                if (i == 2) throw e;
                logger.warn("Retrying due to optimistic lock failure...");
            }
        }
    }

}
