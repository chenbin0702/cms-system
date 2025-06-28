# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**CMS System** is a modern content management system built with Spring Boot 3.5.0 and Java 21. It's a multi-module Maven project designed for rapid development of enterprise content management solutions with comprehensive user management, permission control, content publishing workflows, and code generation capabilities.

## Common Development Commands

### Building and Running
```bash
# Build entire project
mvn clean package

# Build specific service
mvn clean package -pl sz-service/sz-service-admin

# Run admin service locally
mvn spring-boot:run -pl sz-service/sz-service-admin -Dspring-boot.run.profiles=local

# Run with JAR file
java -jar sz-service/sz-service-admin/target/sz-service-admin-1.2.4-beta-SNAPSHOT.jar --spring.profiles.active=local
```

### Code Quality
```bash
# Check code formatting
mvn spotless:check

# Apply code formatting
mvn spotless:apply

# Run tests (currently skipped by default)
mvn test -DskipTests=false
```

### Database Operations
```bash
# Flyway migrations (manual if needed)
mvn flyway:migrate -pl sz-service/sz-service-admin
mvn flyway:info -pl sz-service/sz-service-admin
```

## Architecture Overview

### Multi-Module Structure
- **sz-build/**: Build configuration parent POM
- **sz-dependencies/**: Centralized dependency management
- **sz-common/**: Shared libraries organized by functionality
  - Core utilities, database support (MySQL/MongoDB/Redis), security, file handling, etc.
- **sz-service/**: Business service modules
  - **sz-service-admin/**: Main admin application (port 9991)
  - **sz-service-websocket/**: WebSocket service

### Key Technologies
- **Authentication**: Sa-Token with JWT support
- **ORM**: MyBatis Flex (enhanced MyBatis)
- **Database**: MySQL 8.0.34+ with HikariCP, Redis for caching
- **API Docs**: Knife4j (Swagger enhancement)
- **Storage**: AWS S3 compatible (MinIO, Aliyun OSS, etc.)
- **Code Generation**: Automated CRUD generation for frontend/backend

### Configuration Management
- External YAML files in `config/` directory
- Profile-based configurations (local, dev, preview, prod)
- Spring Config Import for modular configuration
- Database config: `config/local/mysql.yml`
- Redis config: `config/local/redis.yml`

### Security and Permissions
- Role-based access control with data permissions
- Row-level security with configurable data scopes
- API whitelisting and permission handlers
- Request rate limiting (API debouncing)

### Development Patterns
- Uses Lombok for boilerplate reduction
- ModelMapper for object mapping
- Jackson for JSON processing
- Flyway for database version control (migrations in `src/main/resources/db/`)
- Virtual threads enabled (Java 21 feature)

### Code Generation Module
The `sz-common-generator` module provides automated CRUD code generation for both frontend and backend, including:
- Entity classes with validation
- Service and controller layers
- Vue 3 frontend components
- Database migration scripts

### Testing
- JUnit 5 (Jupiter) for unit tests
- Spring Boot Test for integration tests
- Tests currently skipped by default in build configuration

## Application URLs (Local Development)
- CMS API: `http://localhost:9991/api/admin`
- API Documentation: Available via Knife4j when running

## System Requirements
- JDK >= 21
- MySQL >= 8.0.34
- Maven >= 3.8
- Node >= 18.x (for frontend development)