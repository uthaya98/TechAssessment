# 📚 Customer & Product Management API

A **Spring Boot 3.5.0** RESTful API built using **Java 17** to manage Customers and Products. This project demonstrates a production-ready backend with features like CRUD operations, structured logging, exception handling, report generation using JasperReports, Swagger documentation, and optional integrations with Kafka and Hazelcast.

---

## 🚀 Tech Stack

- **Java 17**
- **Spring Boot 3.5.0**
- **Spring Web (MVC)**
- **Spring Data JPA**
- **PostgreSQL**
- **Swagger / OpenAPI (springdoc-openapi)**
- **JasperReports** (Excel export support)
- **Kafka** (optional event messaging)
- **Hazelcast** (optional caching)
- **Logback** (for logging)
- **Lombok** (for cleaner code)
- **Maven** (build tool)

---

## 📁 Project Structure

```
src/main/
├── java/com.Etiqa.TechAssessment/
│   ├── config/                # Configuration classes for Swagger, Kafka, JasperReports
│   ├── controller/            # REST API controllers (Customer, Product, Report)
│   ├── entity/                # JPA entities for Customer and Product
│   ├── exception/             # Custom exception handlers
│   ├── repository/            # Spring Data repositories
│   ├── service/               # Service layer with business logic
│   └── TechAssessmentApplication.java
├── resources/
│   ├── jasper/                # Jasper JRXML report templates
│   └── application.properties
```

---

## 🧪 API Documentation

Interactive Swagger UI is available for testing and exploring endpoints.

📍 Access Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🔗 API Endpoints

### Customer Endpoints

| Method | Endpoint              | Description              |
|--------|-----------------------|--------------------------|
| GET    | `/api/customers`      | Get all customers        |
| POST   | `/api/customers`      | Create a new customer    |
| PUT    | `/api/customers/{id}` | Update an existing customer |
| DELETE | `/api/customers/{id}` | Delete a customer        |

### Product Endpoints

| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| GET    | `/api/products`      | Get all products         |
| POST   | `/api/products`      | Create a new product     |
| PUT    | `/api/products/{id}` | Update an existing product |
| DELETE | `/api/products/{id}` | Delete a product         |

### Report Endpoint

| Method | Endpoint                     | Description                  |
|--------|------------------------------|------------------------------|
| GET    | `/api/reports/export/excel`  | Export customer & product data as Excel report (JasperReports) |

---

## 📦 JasperReports Integration

JasperReports is used to generate downloadable Excel reports. The reports are composed of a master report and subreports:

- `customer_product_excel_report.jrxml` - Master report template
- `customer_subreport.jrxml` - Customer data subreport
- `product_subreport.jrxml` - Product data subreport

📤 Use the endpoint `/api/reports/export/excel` to generate and download the report.

---

## ⚙️ Configuration - `application.properties`

```properties
# PostgreSQL Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdb
spring.datasource.username=youruser
spring.datasource.password=yourpassword

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Swagger Configuration
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true

# Kafka (Optional)
spring.kafka.bootstrap-servers=localhost:9092
```

---

## 🚀 Running the Application

### Prerequisites

- Java 17+ installed
- PostgreSQL database running
- Maven installed

### Build & Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Access

- API Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`


---

### Data

- I have upload 2 json files which are sample_product.json(Consists of Sample data of products) and sample_customer.json(Consists of Sample data of Customers) for testing

---
## 👨‍💻 Author

**Uthaya Surian**  
📧 uthayasurian98@gmail.com  
🔗 GitHub: [https://github.com/uthaya98](https://github.com/uthaya98)
