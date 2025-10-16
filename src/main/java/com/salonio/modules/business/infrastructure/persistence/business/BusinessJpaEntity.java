package com.salonio.modules.business.infrastructure.persistence.business;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business")
public class BusinessJpaEntity {
        @Id
        private UUID id;

        @Version
        private Integer version;

        private String businessName;

        private LocalDateTime creationDate;

        private String address;

        private String email;

        private String phoneNumber;

        private String websiteUrl;

        private String taxId;

        private String city;

        private String postalCode;

        private String country;

        private Double latitude, longitude;

        private String businessType;

        //private List<Service> servicesOffered;

        //List<Staff> staffMembers;

        private LocalTime openingTime, closingTime;

        private boolean isActive;

        private double rating;

        private int numberOfReviews;

        private int totalBookings;

        private String logoUrl;

        private String description;

        private String currency;

        private boolean scheduling;

}
