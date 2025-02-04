
# Book Store API

Welcome to the Book Store API! This project provides a RESTful API for managing a collection of books. You can add, update, delete, and view books in the store. The API is built with Spring Boot and uses Docker for containerization.

## How to Run the Book Store

Follow these steps to set up and run the project locally:

### Prerequisites

1. Install [Docker](https://www.docker.com/products/docker-desktop)
2. Install [Maven](https://maven.apache.org/install.html)
3. Install [JDK]



### Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/Kolyanchik1115/BookShop.git
   ```

2. **Configure Environment Variables**

   Create a `.env` file in the root directory and configure your database settings:

   ```dotenv
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/book_store
   SPRING_DATASOURCE_USERNAME=yourusername
   SPRING_DATASOURCE_PASSWORD=yourpassword
   ```

3. **Update `application.properties`**

   Ensure your `application.properties` file in `src/main/resources` matches the `.env` configuration:

   ```properties
   spring.datasource.url=${SPRING_DATASOURCE_URL}
   spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
   spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
   ```

### Build and Run

1. **Build the Project**

   Run the following command to clean and package the project:

   ```bash
   mvn clean package
   ```

2. **Build and Run Docker Containers**

   Use Docker Compose to build and run the containers:

   ```bash
   docker-compose up --build
   ```

3. **Access the Application**

   The application will be available at: [http://localhost:8088/api](http://localhost:8088/api)

## API Endpoints

Here are the available endpoints for the Book Store API:

### Books

- **Get All Books**

  ```http
  GET /api/books
  ```

- **Get Book by ID**

  ```http
  GET /api/books/{id}
  ```

- **Create a New Book**

  ```http
  POST /api/books
  ```

  **Request Body:**

  ```json
  {
    "title": "string",
    "author": "string",
    "isbn": "string",
    "price": "number",
    "description": "string",
    "categories": ["number"]
  }
  ```

- **Update a Book**

  ```http
  PUT /api/books/{id}
  ```

  **Request Body:**

  ```json
  {
    "title": "string",
    "author": "string",
    "isbn": "string",
    "price": "number",
    "description": "string",
    "categories": ["number"]
  }
  ```

- **Delete a Book**

  ```http
  DELETE /api/books/{id}
  ```

## Running Tests

You can run the tests using Maven:

```bash
mvn test
```
## Running Tests

## API Documentation

### Swagger UI

To conveniently view and test the API, you can use Swagger UI:

[Swagger UI](http://localhost:8088/api/swagger-ui/index.html)

### Postman Collection

For testing the API, you can also use Postman:

[Postman Collection](https://documenter.getpostman.com/view/24380148/2sA3kYhKew)
