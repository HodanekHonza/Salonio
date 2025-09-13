package com.salonio.staff.internal;

public final class StaffFactory {

    private StaffFactory() {
        throw new AssertionError();
    }

    public static Staff create() {
        return new Staff();
    }
}
