# Salonio

> Event-driven booking platform with hexagonal architecture, demonstrating production-ready Spring Boot development practices.

[Live Demo](https://salonio.onrender.com) | [API Documentation](https://salonio.onrender.com/swagger-ui.html)

## What It Does

A complete salon booking system handling real-world challenges:
- **Real-time availability management** with conflict detection and automatic slot updates
- **Shared service catalog** accessible to all businesses and clients
- **Event-driven architecture** ensuring data consistency across bounded contexts
- **RESTful APIs** with comprehensive OpenAPI documentation

Built to demonstrate clean architecture, domain-driven design, and event-sourcing patterns in a production-ready context.

## Key Features

### Booking Module
- Conflict-free appointment scheduling with optimistic locking
- Automatic availability updates via domain events
- Full booking lifecycle management (pending → confirmed → rescheduled → cancelled)
- Event-driven state transitions

### Availability Module
- Availability slot management
- Real-time slot creation and updates
- Integration with booking events for automatic synchronization
- Conflict detection

### Business Module
- Shared service catalog management
- Review and rating system with validation
- Business and service categorization
- Hierarchical category structure
- Business profile management

## Architecture Highlights

This project implements **hexagonal architecture** (Ports & Adapters pattern) with clear separation of concerns:

- **Domain-Driven Design** with properly bounded contexts
- **Event-driven communication** between modules using Spring Events
- **Clean layering**: API → Application → Domain → Infrastructure
- **Port/Adapter pattern** for external dependencies
- **Transaction safety** with optimistic locking

```
┌─────────────────────────────────────────────────────────┐
│                     REST API Layer                      │
│              (Controllers, DTOs, OpenAPI)               │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│                 Application Layer                       │
│        (Services, Factories, Ports/Interfaces)          │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│                   Domain Layer                          │
│        (Entities, Value Objects, Domain Events)         │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────────────┐
│               Infrastructure Layer                      │
│    (JPA Adapters, Event Listeners, Repositories)        │
└─────────────────────────────────────────────────────────┘
```

## 🚀 Tech Stack

**Backend:** Java 17, Spring Boot 3, Spring Data JPA  
**Database:** PostgreSQL  
**Events:** Spring Application Events (Kafka-ready architecture)  
**Security:** Spring Security with JWT authentication  
**Testing:** JUnit 5, Mockito  
**API Documentation:** OpenAPI 3.0 / Swagger UI  
**Build Tool:** Maven

## 📊 Project Status

| Module | Status              | Description                                                             |
|--------|---------------------|-------------------------------------------------------------------------|
| **Booking** | **Complete**        | Full hexagonal architecture with event-driven design, conflict handling |
| **Availability** | **Complete**        | Slot management with real-time conflict detection and event integration |
| **Business** | **Almost Complete** | Complete CRUD for businesses, services, categories, and reviews         |
| **User** | In Progress         | Authentication system with Spring Security and JWT                      |
| **Client** | In Progress          | Client profile management and preferences                               |
| **Staff** |  In Progress          | Staff scheduling and management system                                  |
| **Payment** | 📋 Planned          | Payment processing integration (Stripe)                                 |
| **Notification** | 📋 Planned          | Event-driven email/SMS notifications (Twilio)                           |

**Core booking functionality is production-ready and fully tested.** Additional modules demonstrate architectural consistency and project roadmap.

## What I Learned Building This

- Implementing **hexagonal architecture** in a real-world Spring Boot application
- Managing **distributed data consistency** using domain events
- Designing **RESTful APIs** with proper separation of concerns
- Handling **concurrent operations** with optimistic locking
- Balancing **architectural purity** with pragmatic delivery timelines
- Building **event-driven systems** that maintain data consistency across modules
- Applying **SOLID principles** and clean code practices in production code

## Quick Start

### Prerequisites
- Java 17+
- Docker & Docker Compose (recommended)
- Maven 3.8+

### Running Locally

```bash
# Clone the repository
git clone https://github.com/HodanekHonza/Salonio.git
cd salonio


# Access the application
open http://localhost:8080/swagger-ui.html
```

### Testing the API

The easiest way to explore the API is through the **Swagger UI** at `http://localhost:8080/swagger-ui.html`

Alternatively, use the Bruno API collections in `src/main/resources/bruno/`:
- **Booking Module** - Complete booking lifecycle operations
- **User Module** - Authentication and authorization flows
- *(Additional collections coming soon)*

## Project Structure

```
salonio/
├── modules/
│   ├── availability/           # Availability management (Complete)
│   │   ├── api/               # REST contracts & DTOs
│   │   ├── application/       # Services, factories, ports
│   │   ├── domain/            # Entities, events, business rules
│   │   ├── infrastructure/    # Controllers, persistence, messaging
│   │   └── exception/         # Module-specific exceptions
│   │
│   ├── booking/               # Booking lifecycle (Complete)
│   │   ├── api/
│   │   ├── application/
│   │   ├── domain/
│   │   ├── infrastructure/
│   │   └── exception/
│   │
│   ├── business/              # Business operations (Complete)
│   │   ├── api/
│   │   ├── application/
│   │   ├── domain/
│   │   ├── infrastructure/
│   │   └── exception/
│   │
│   ├── user/                  # 🚧 Authentication (In Progress)
│   ├── client/                # 📋 Planned
│   ├── staff/                 # 📋 Planned
│   ├── payment/               # 📋 Planned
│   ├── notification/          # 📋 Planned
│   │
│   └── common/                # Shared utilities and base classes
│       ├── event/             # Event publishing infrastructure
│       ├── util/              # Security utilities
│       └── exception/         # Base exception classes
│
└── Application.java           # Main Spring Boot application
```

### Module Structure

Each complete module follows **hexagonal architecture**:

- **`api/`** - Public API contracts, DTOs, and interfaces for external consumers
- **`application/`** - Business logic, use cases, factories, and port definitions
    - `service/` - Application services orchestrating use cases
    - `factory/` - Domain object factories
    - `port/out/` - Interfaces for external dependencies (persistence, events)
- **`domain/`** - Core business entities, value objects, and domain events
- **`infrastructure/`** - Implementation of ports and framework-specific code
    - `controller/` - REST controllers
    - `persistence/` - JPA entities, repositories, adapters
    - `messaging/` - Event listeners
- **`exception/`** - Module-specific exceptions and handlers

## Testing

```bash
# Run all tests
./gradlew test
```

**Testing Strategy:**
- **Unit Tests** - Business logic in application services and domain layer
- **Integration Tests** - API endpoints and persistence layer (in progress)

Core modules (Booking, Availability) have comprehensive unit test coverage.

## API Documentation

### Interactive Documentation
When the application is running, access interactive API documentation at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### API Collections
Bruno API collections are available in `src/main/resources/bruno/` for:
- **Booking Module** - Create, update, cancel bookings
- **User Module** - Registration, login, JWT authentication
- **Business Module** - Coming soon
- **Availability Module** - Coming soon

## Key Design Decisions

### Why Hexagonal Architecture?
- **Testability**: Business logic is independent of frameworks
- **Flexibility**: Easy to swap infrastructure components (database, messaging)
- **Maintainability**: Clear boundaries and dependencies

### Why Event-Driven?
- **Decoupling**: Modules communicate without direct dependencies
- **Consistency**: Automatic data synchronization across bounded contexts
- **Auditability**: Event log provides complete system history
- **Scalability**: Ready for distributed messaging (Kafka) when needed

### Why Modular Monolith?
- **Team Scalability**: Different teams can own different modules
- **Migration Path**: Easy to extract modules into microservices later
- **Operational Simplicity**: Single deployment while maintaining boundaries

## 🛠️ Development Guidelines

### Code Principles
- Follow **SOLID principles** in all service and domain design
- Use **Optional** to handle potentially absent values safely
- Keep **business logic** in Application/Domain layers
- Infrastructure layer only handles **orchestration and adaptation**
- Use **DTOs** for all API inputs and outputs
- Write **meaningful tests** - not just for coverage

### Event Guidelines
- Domain events are **immutable** and represent facts
- Events should be **named in past tense** (BookingCreatedEvent)
- Event handlers should be **idempotent**
- Never throw exceptions in event listeners

### Testing Guidelines
- **Unit tests** for services: Mock dependencies, test business logic
- **Integration tests** for controllers: Test full request/response cycle
- Test **edge cases** and error conditions
- Use **meaningful test names** that describe behavior

## License

MIT License - feel free to use this project for learning or as a reference.

---

## About This Project

Salonio was built as a demonstration of production-ready backend development practices, focusing on:
- Clean, maintainable architecture
- Proper separation of concerns
- Real-world business logic handling
- Event-driven design patterns
- Comprehensive API design

---

**⭐ If you find this project helpful or interesting, please consider starring it!**