package com.salonio.modules.business.domain;

import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;
import java.util.UUID;

public class Review {

    private UUID id;

    private Integer version;

    private String text;

    private UUID clientId;

    private UUID businessId;

    private Integer rating;

    public Review(UUID id, Integer version, String text, UUID clientId, UUID businessId, Integer rating) {
        this.id = id;
        this.version = version;
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

    public Review updateEntity(ReviewUpdateRequest request) {
        if (request.text() != null) this.text = request.text();
        if (request.clientId() != null) this.clientId = request.clientId();
        if (request.businessId() != null) this.businessId = request.businessId();
        if (request.rating() != null) this.rating = request.rating();
        return this;
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
        return Objects.equals(id, review.id)
                && Objects.equals(version, review.version)
                && Objects.equals(text, review.text)
                && Objects.equals(clientId, review.clientId)
                && Objects.equals(businessId, review.businessId)
                && Objects.equals(rating, review.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                version,
                text,
                clientId,
                businessId,
                rating
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("text", text)
                .append("clientId", clientId)
                .append("businessId", businessId)
                .append("rating", rating)
                .toString();
    }

}
