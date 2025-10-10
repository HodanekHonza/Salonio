package com.salonio.modules.staff.domain;

import com.salonio.modules.staff.api.dto.StaffUpdateRequest;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Staff {

    private UUID id;

    private Integer version;

    private UUID userId;

    private String firstName;

    private String lastName;

    private UUID businessId;

    private String phoneNumber;

    @Email
    private String email;

    @Email
    private String workEmail;

    private String workPhoneNumber;

    private String role;
    private String specialization;

    private Boolean isActive = true;

    private LocalDate hireDate;
    private LocalDate terminationDate;

    private BigDecimal salary;
    private String employmentType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Staff(Integer version, UUID userId, String firstName, String lastName, UUID businessId, String phoneNumber, String email, String workEmail, String workPhoneNumber, String role, String specialization, Boolean isActive, LocalDate hireDate, LocalDate terminationDate, BigDecimal salary, String employmentType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = UUID.randomUUID();
        this.version = version;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.businessId = businessId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workEmail = workEmail;
        this.workPhoneNumber = workPhoneNumber;
        this.role = role;
        this.specialization = specialization;
        this.isActive = isActive;
        this.hireDate = hireDate;
        this.terminationDate = terminationDate;
        this.salary = salary;
        this.employmentType = employmentType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Staff(UUID userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
    }

    public Staff updateEntity(StaffUpdateRequest request) {
        if (request.firstName() != null) this.firstName = request.firstName();
        if (request.lastName() != null) this.lastName = request.lastName();
        if (request.phoneNumber() != null) this.phoneNumber = request.phoneNumber();
        if (request.email() != null) this.email = request.email();
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Staff staff)) return false;
        return Objects.equals(id, staff.id)
                && Objects.equals(version, staff.version)
                && Objects.equals(userId, staff.userId)
                && Objects.equals(firstName, staff.firstName)
                && Objects.equals(lastName, staff.lastName)
                && Objects.equals(businessId, staff.businessId)
                && Objects.equals(phoneNumber, staff.phoneNumber)
                && Objects.equals(email, staff.email)
                && Objects.equals(workEmail, staff.workEmail)
                && Objects.equals(workPhoneNumber, staff.workPhoneNumber)
                && Objects.equals(role, staff.role)
                && Objects.equals(specialization, staff.specialization)
                && Objects.equals(isActive, staff.isActive)
                && Objects.equals(hireDate, staff.hireDate)
                && Objects.equals(terminationDate, staff.terminationDate)
                && Objects.equals(salary, staff.salary)
                && Objects.equals(employmentType, staff.employmentType)
                && Objects.equals(createdAt, staff.createdAt)
                && Objects.equals(updatedAt, staff.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                version,
                userId,
                firstName,
                lastName,
                businessId,
                phoneNumber,
                email,
                workEmail,
                workPhoneNumber,
                role,
                specialization,
                isActive,
                hireDate,
                terminationDate,
                salary,
                employmentType,
                createdAt,
                updatedAt
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("version", version)
                .append("userId", userId)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("businessId", businessId)
                .append("phoneNumber", phoneNumber)
                .append("email", email)
                .append("workEmail", workEmail)
                .append("workPhoneNumber", workPhoneNumber)
                .append("role", role)
                .append("specialization", specialization)
                .append("isActive", isActive)
                .append("hireDate", hireDate)
                .append("terminationDate", terminationDate)
                .append("salary", salary)
                .append("employmentType", employmentType)
                .append("createdAt", createdAt)
                .append("updatedAt", updatedAt)
                .toString();
    }

}
