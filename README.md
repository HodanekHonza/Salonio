**Salonio** is a modular monolith application for managing bookings, availability, clients, staff, payments, notifications, and offerings for a salon management system. It follows **clean architecture principles** (SOLID, DRY, KISS) and is designed for maintainability, scalability, and extensibility.

---

## Table of Contents

- [Features](#features)  
- [Architecture](#architecture)  
- [Modules and Libraries](#modules-and-libraries)  
- [Technologies](#technologies)  
- [Getting Started](#getting-started)  
- [Project Structure](#project-structure)  
- [Development Guidelines](#development-guidelines)  
- [Testing](#testing)  
- [Contributing](#contributing)  
- [License](#license)  

---

## Features

- Full booking and availability management  
- Client, staff, business, and offering management  
- Notifications and payment handling  
- Event-driven communication between modules  
- Centralized exception handling  
- Transaction-safe operations with optimistic locking and retry mechanisms  
- RESTful APIs for each module  
- Modular design allowing easy reuse of modules as libraries  

---

## Architecture

Salonio follows a **modular monolith architecture** with **clean separation of concerns**:

- **API Layer** – REST endpoints and DTOs for external clients  
- **Application Layer** – Services, factories, and ports (interfaces) for business logic  
- **Domain Layer** – Core entities, value objects, and domain events  
- **Infrastructure Layer** – Controllers, messaging listeners, persistence adapters (JPA), and repositories  
- **Exception Layer** – Centralized exception handling and custom exceptions  

Event-driven communication uses Spring's **ApplicationEventPublisher** and listeners to decouple modules.

---

## Modules and Libraries

Salonio uses a modular structure:

- **Modules** (`modules/`) – Core functional modules:
  - `availability` – Staff availability management  
  - `booking` – Booking lifecycle management  
  - `business` – Business profile management  
  - `client` – Client management and authentication  
  - `notification` – Notifications and events  
  - `offering` – Salon offerings  
  - `payment` – Payment handling  
  - `staff` – Staff management and scheduling  
  - `user` – User authentication and authorization  

- **Libraries** (`libs/common/`) – Shared utilities, DTOs, exceptions, and helpers reused across modules.

---

## Technologies

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- PostgreSQL (or other relational DB)  
- Docker (for containerized local development)  
- Lombok  
- SLF4J + Logback  
- Event-driven architecture via Spring Events  

---

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/HodanekHonza/Salonio.git
cd salonio
````
coming 

---

## Project Structure

```text
salonio/
├── modules/
│   ├── availability/
│   ├── booking/
│   ├── business/
│   ├── client/
│   ├── notification/
│   ├── offering/
│   ├── payment/
│   ├── staff/
│   └── user/
├── libs/
│   └── common/
├── Application.java
```

Each module contains:

* `api/` – REST API interfaces and DTOs
* `application/` – Services, factories, ports (in/out)
* `domain/` – Entities, enums, and events
* `exception/` – Module-specific exceptions
* `infrastructure/` – Controllers, persistence adapters, messaging listeners

---

## Development Guidelines

* Follow **SOLID principles** in service and domain design
* Use **Optional** to handle absent values safely
* Keep business logic in **Application/Domain layer**, infrastructure only handles orchestration
* Use centralized exception handling across modules
* Use DTOs for all API inputs and outputs
* Write **unit tests** for services and **integration tests** for controllers

---

## Testing

* Unit tests: `src/test/java/{module}/application/service`
* Integration tests: `src/test/java/{module}/infrastructure/controller`

---

