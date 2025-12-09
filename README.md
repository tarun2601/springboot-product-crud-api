# **📦 Spring Boot Product CRUD API**

A simple Spring Boot REST API that performs CRUD operations on Product data.
Includes validation, service layer architecture, DTO usage, a global JSON response wrapper, and proper REST endpoints.


----------------------------------------------------------------------------------------------
# 🚀 Features

- Create product
- Get product by ID
- Get all products
- Update product
- Delete product
- Validation and Authentication
- Uses layered architecture (Controller → Service → Repository → Entity)
- JSON response wrapper (JsonResponse<T>)

---------------------------------------------------------------------------------------
# 🧱 Tech Stack

- Java 21
- Spring Boot 3+
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL (any relational DB)
- Lombok

-----------------------------------------------------------------------------
# Project Structure

* src/main/java/com/company/product_api/
* │
* ├─ config/
* |    └─ SecurityConfig.java
* │
* ├─ controller/
* │   └─ ProductController.java
* │
* ├─ dto/
* │   ├─ ProductDTO.java
* │   └─ JsonResponse.java
* │
* ├─ exception/
* │   ├─ GlobalExceptionHandler.java
* │   └─ ResourceNotFoundException.java
* │
* ├─ model/
* │   └─ Product.java
* │
* ├─ repository/
* │   ├─ ProductRepository.java
* │
* ├─ service/
* │   ├─ ProductService.java
* │   └─ ProductServiceImpl.java
* │
* └─ ProductApiApplication.java

-------------------------------------------------------------------------------------------
# Security Configuration

This project includes basic authentication with in-memory users and role-based API access control.

#### 👥 In-Memory Users

Two users are configured:

    Username	Password	    Role
    admin	    admin123	    ADMIN
    user	    user123	        USER

### Security Rules

 Public (No authentication required)

* /swagger-ui/**
* /v3/api-docs/**
* /swagger-ui.html
* /api-docs/**

These URLs allow access to Swagger UI and OpenAPI docs without login.

Protected API Access

#### USER Role (Read-only access)

- USER can only perform GET requests:

        Method	    Endpoint	       Access
        GET	        /products/**     USER, ADMIN

#### ADMIN Role (Full CRUD access)

- ADMIN can perform ALL operations:

        Method	Endpoint	    Access
        POST	/products/**	ADMIN
        PUT	    /products/**	ADMIN
        DELETE	/products/**	ADMIN

-------------------------------------------------------------------------------------------

# API Endpoints

## **Products**

* GET /products: Retrieve all products.
* GET /products/{id}: Retrieve a product by ID.
* POST /products: Create a new product.
* PUT /products/{id}: Update a product by ID.
* DELETE /products/{id}: Delete a product by ID.

-------------------------------------------------------------------
# Custom Error Messages

The API includes custom error messages for validation failures. These messages are returned in the response JSON.


-------------------------------------------------------
# 🛠 Setup Instructions

### 1. Clone repository

      git clone https://github.com/tarun2601/springboot-product-crud-api.git
      cd springboot-product-crud-api

### 2. Configure Database (application.properties)

      spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
      spring.datasource.username=yourusername
      spring.datasource.password=yourpassword
      spring.jpa.hibernate.ddl-auto=update

### 3. Build the project
      ./gradlew build

### 4. Run the application
      ./gradlew bootRun

### 5. Once the application is running, you can access the API documentation at:
       http://localhost:8080/swagger-ui/index.html


