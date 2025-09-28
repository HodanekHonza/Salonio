package com.salonio.modules.business.domain;

import java.util.Objects;
import java.util.UUID;

public class Review {

    private UUID id;
    private String text;
    private UUID clientId;
    private UUID businessId;
    private Integer rating;

    public Review(UUID id, String text, UUID clientId, UUID businessId, Integer rating) {
        this.id = id;
        this.text = text;
        this.clientId = clientId;
        this.businessId = businessId;
        this.rating = rating;
    }

    public Review(String text, UUID clientId, UUID businessId, Integer rating) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.clientId = clientId;
        this.businessId = businessId;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public UUID getBusinessId() {
        return businessId;
    }

    public void setBusinessId(UUID businessId) {
        this.businessId = businessId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Review review)) return false;
        return Objects.equals(id, review.id) && Objects.equals(text, review.text) && Objects.equals(clientId, review.clientId)
                && Objects.equals(businessId, review.businessId) && Objects.equals(rating, review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, clientId, businessId, rating);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", clientId=" + clientId +
                ", businessId=" + businessId +
                ", rating=" + rating +
                '}';
    }

}
