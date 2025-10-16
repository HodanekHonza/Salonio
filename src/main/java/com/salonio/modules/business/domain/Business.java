package com.salonio.modules.business.domain;

import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Business {

    private UUID id;

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

    private Double latitude;

    private Double longitude;

    private String businessType;

    private List<Service> servicesOffered;

    //    List<Staff> staffMembers;

    private LocalTime openingTime;

    private LocalTime closingTime;

    private boolean isActive;

    private double rating;

    private int numberOfReviews;

    private int totalBookings;

    private String logoUrl;

    private String description;

    private String currency;

    private boolean scheduling = false;

    public Business(UUID id, Integer version, String businessName, LocalDateTime creationDate,
                    String address, String email, String phoneNumber, String websiteUrl, String taxId,
                    String city,String postalCode, String country, Double latitude, Double longitude,
                    String businessType, LocalTime openingTime, LocalTime closingTime, boolean isActive,
                    double rating, int numberOfReviews, int totalBookings, String logoUrl,
                    String description, String currency, boolean isScheduling) {
        this.id = id;
        this.version = version;
        this.businessName = businessName;
        this.creationDate = creationDate;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.websiteUrl = websiteUrl;
        this.taxId = taxId;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.businessType = businessType;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.isActive = isActive;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.totalBookings = totalBookings;
        this.logoUrl = logoUrl;
        this.description = description;
        this.currency = currency;
        this.scheduling = isScheduling;
    }

    public Business(String businessName, LocalDateTime creationDate,
                    String address, String email, String phoneNumber, String websiteUrl, String taxId,
                    String city,String postalCode, String country, Double latitude, Double longitude,
                    String businessType, LocalTime openingTime, LocalTime closingTime, boolean isActive,
                    double rating, int numberOfReviews, int totalBookings, String logoUrl,
                    String description, String currency) {
        this.id = UUID.randomUUID();
        this.businessName = businessName;
        this.creationDate = creationDate;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.websiteUrl = websiteUrl;
        this.taxId = taxId;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.businessType = businessType;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.isActive = isActive;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
        this.totalBookings = totalBookings;
        this.logoUrl = logoUrl;
        this.description = description;
        this.currency = currency;
    }

    public Business updateEntity(BusinessUpdateRequest request) {
        if (request.businessName() != null) this.businessName = request.businessName();
        if (request.address() != null) this.address = request.address();
        if (request.email() != null) this.email = request.email();
        if (request.phoneNumber() != null) this.phoneNumber = request.phoneNumber();
        if (request.websiteUrl() != null) this.websiteUrl = request.websiteUrl();
        if (request.taxId() != null) this.taxId = request.taxId();
        if (request.city() != null) this.city = request.city();
        if (request.postalCode() != null) this.postalCode = request.postalCode();
        if (request.country() != null) this.country = request.country();
        if (request.latitude() != null) this.latitude = request.latitude();
        if (request.longitude() != null) this.longitude = request.longitude();
        if (request.businessType() != null) this.businessType = request.businessType();
        if (request.openingTime() != null) this.openingTime = request.openingTime();
        if (request.closingTime() != null) this.closingTime = request.closingTime();
        this.isActive = request.isActive();
        if (request.logoUrl() != null) this.logoUrl = request.logoUrl();
        if (request.description() != null) this.description = request.description();
        if (request.currency() != null) this.currency = request.currency();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Business business)) return false;
        return isActive == business.isActive
                && Double.compare(rating, business.rating) == 0
                && numberOfReviews == business.numberOfReviews
                && totalBookings == business.totalBookings
                && Objects.equals(id, business.id)
                && Objects.equals(version, business.version)
                && Objects.equals(businessName, business.businessName)
                && Objects.equals(creationDate, business.creationDate)
                && Objects.equals(address, business.address)
                && Objects.equals(email, business.email)
                && Objects.equals(phoneNumber, business.phoneNumber)
                && Objects.equals(websiteUrl, business.websiteUrl)
                && Objects.equals(taxId, business.taxId)
                && Objects.equals(city, business.city)
                && Objects.equals(postalCode, business.postalCode)
                && Objects.equals(country, business.country)
                && Objects.equals(latitude, business.latitude)
                && Objects.equals(longitude, business.longitude)
                && Objects.equals(businessType, business.businessType)
                && Objects.equals(openingTime, business.openingTime)
                && Objects.equals(closingTime, business.closingTime)
                && Objects.equals(logoUrl, business.logoUrl)
                && Objects.equals(description, business.description)
                && Objects.equals(currency, business.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                version,
                businessName,
                creationDate,
                address,
                email,
                phoneNumber,
                websiteUrl,
                taxId,
                city,
                postalCode,
                country,
                latitude,
                longitude,
                businessType,
                openingTime,
                closingTime,
                isActive,
                rating,
                numberOfReviews,
                totalBookings,
                logoUrl,
                description,
                currency
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("businessName", businessName)
                .append("creationDate", creationDate)
                .append("address", address)
                .append("email", email)
                .append("phoneNumber", phoneNumber)
                .append("websiteUrl", websiteUrl)
                .append("taxId", taxId)
                .append("city", city)
                .append("postalCode", postalCode)
                .append("country", country)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("businessType", businessType)
                .append("openingTime", openingTime)
                .append("closingTime", closingTime)
                .append("isActive", isActive)
                .append("rating", rating)
                .append("numberOfReviews", numberOfReviews)
                .append("totalBookings", totalBookings)
                .append("logoUrl", logoUrl)
                .append("description", description)
                .append("currency", currency)
                .toString();
    }

}
