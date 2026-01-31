# Fitmono - Fitness Tracking Monolith

Fitmono is a robust fitness tracking application built with **Spring Boot 4.0.2** and **Java 21**. It provides core functionalities for user management and activity tracking, serving as a foundational monolith that is being prepared for transition into a distributed microservices architecture.

## 🚀 Technologies

- **Java 21**: Leveraging the latest LTS features for performance and modern syntax.
- **Spring Boot 4.0.2**: Utilizing the newest Spring ecosystem for rapid backend development.
- **Spring Data JPA**: For streamlined database interactions and ORM.
- **MySQL 8.0**: Persistent relational storage for user and activity data.
- **Lombok**: To reduce boilerplate code in models and DTOs.
- **OrbStack**: Professional-grade containerization used to manage the MySQL environment efficiently on macOS.

## 🏗️ Architecture & Features

### Core Monolith Features
- **User Management**: Comprehensive registration and profile handling.
- **Activity Tracking**: Recording and managing fitness activities with support for JSON-based additional metrics.
- **JSON Column Mapping**: Utilizing Hibernate 7's advanced mapping capabilities for flexible activity data storage.

### Infrastructure
- **Database**: Running a containerized MySQL instance via OrbStack at `jdbc:mysql://mysql-container.orb.local:3306/fitness_db`.
- **Automated Schema Updates**: Configured with `hibernate.ddl-auto=update` for seamless development iterations.

## 🛣️ Roadmap to Microservices

This project is currently being refactored to separate concerns into independent services:
1. **User Service**: Dedicated service for authentication and profile management.
2. **Activity Service**: Specialized service for high-volume activity data processing.
3. **Event-Driven Architecture**: Planned integration of **Apache Kafka** for inter-service communication.
4. **NoSQL Integration**: Migration of analytical data to **MongoDB** to handle diverse fitness metrics.

## 🔧 Setup & Installation

1. **Prerequisites**: Ensure you have **Java 21** and **Maven** installed.
2. **Database**: Launch your MySQL container. The application is configured to connect via OrbStack.
3. **Build**: 
   ```bash
   mvn clean install
