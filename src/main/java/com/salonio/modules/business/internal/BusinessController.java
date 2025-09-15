package com.salonio.modules.business.internal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping
    void createBusiness() {

    }

    @GetMapping("/{businessId}")
    void getBusiness(@PathVariable String businessId) {

    }

    @PutMapping()
    void updateBusiness() {

    }

    @DeleteMapping()
    void deleteBusiness() {

    }


}
