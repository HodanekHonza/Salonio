package com.salonio.modules.business.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

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

    private Double latitude, longitude;

    private String businessType;

//    private List<Service> servicesOffered;

    //    List<Staff> staffMembers;

    private LocalTime openingTime, closingTime;

    private boolean isActive;

    private double rating;

    private int numberOfReviews;

    private int totalBookings;

    private String logoUrl;

    private String description;

    private String currency;

    public Business(UUID id, Integer version, String businessName, LocalDateTime creationDate,
                    String address, String email, String phoneNumber, String websiteUrl, String taxId,
                    String city,String postalCode, String country, Double latitude, Double longitude,
                    String businessType, LocalTime openingTime, LocalTime closingTime, boolean isActive,
                    double rating, int numberOfReviews, int totalBookings, String logoUrl,
                    String description, String currency) {
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
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public int getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(int totalBookings) {
        this.totalBookings = totalBookings;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Business business)) return false;
        return isActive == business.isActive && Double.compare(rating, business.rating) == 0
                && numberOfReviews == business.numberOfReviews && totalBookings == business.totalBookings
                && Objects.equals(id, business.id) && Objects.equals(version, business.version)
                && Objects.equals(businessName, business.businessName)
                && Objects.equals(creationDate, business.creationDate) && Objects.equals(address, business.address)
                && Objects.equals(email, business.email) && Objects.equals(phoneNumber, business.phoneNumber)
                && Objects.equals(websiteUrl, business.websiteUrl) && Objects.equals(taxId, business.taxId)
                && Objects.equals(city, business.city) && Objects.equals(postalCode, business.postalCode)
                && Objects.equals(country, business.country) && Objects.equals(latitude, business.latitude)
                && Objects.equals(longitude, business.longitude) && Objects.equals(businessType, business.businessType)
                && Objects.equals(openingTime, business.openingTime) && Objects.equals(closingTime, business.closingTime)
                && Objects.equals(logoUrl, business.logoUrl) && Objects.equals(description, business.description)
                && Objects.equals(currency, business.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, businessName, creationDate, address, email, phoneNumber, websiteUrl,
                taxId, city, postalCode, country, latitude, longitude, businessType, openingTime, closingTime,
                isActive, rating, numberOfReviews, totalBookings, logoUrl, description, currency);
    }

    @Override
    public String toString() {
        return "Business{" +
                "id=" + id +
                ", version=" + version +
                ", businessName='" + businessName + '\'' +
                ", creationDate=" + creationDate +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", taxId='" + taxId + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", businessType='" + businessType + '\'' +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", isActive=" + isActive +
                ", rating=" + rating +
                ", numberOfReviews=" + numberOfReviews +
                ", totalBookings=" + totalBookings +
                ", logoUrl='" + logoUrl + '\'' +
                ", description='" + description + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

}
