package com.salonio.staff.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface StaffRepository extends JpaRepository<Staff, UUID> {
}