package com.salonio.staff.internal;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    void createStaff() {
        staffService.createStaff();
    }

    @GetMapping("/{staffId}")
    void getStaff(@PathVariable UUID staffId) {
        staffService.getStaff(staffId);
    }

    @PutMapping("/{staffId}")
    void updateStaff(@PathVariable UUID staffId) {
        staffService.updateStaff();
    }

    @DeleteMapping("/{staffId}")
    void deleteStaff(@PathVariable UUID staffId) {
        staffService.deleteStaff();
    }

}
