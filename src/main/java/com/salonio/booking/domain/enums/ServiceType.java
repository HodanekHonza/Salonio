package com.salonio.booking.domain.enums;

public enum ServiceType {
    HAIRCUT("Haircut"),
    MANICURE("Manicure"),
    PEDICURE("Pedicure"),
    MASSAGE("Massage"),
    COLORING("Coloring");

    private final String displayName;

    ServiceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
