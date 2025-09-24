package com.salonio.modules.business.application.service;

import com.salonio.modules.business.domain.Business;
import com.salonio.modules.business.infrastructure.persistence.BusinessRepository;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;

    BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    void createBusiness(Business business) {

    }

    void getBusiness() {


    }

    void  updateBusiness(Business business) {

    }
    void deleteBusiness(Business business) {

    }

}
