
# Book Management Service

This project is a Book Management Service, which includes CRUD operations for managing books and a book borrowing service. The project is built using Spring Boot and includes the Eureka Service for service registration and discovery.

## Source Code

The source code for this project is available on GitHub: [Book Management Service](https://github.com/JaykumarPatel1998/BookManagementService)

## Services

### Book Service (CRUD Operations)

1. **GET /books/list**
   - Retrieves a list of all books.

2. **GET /books?id=1**
   - Retrieves a book by its ID.

3. **POST /books**
   - Adds a new book.

4. **PUT /books**
   - Updates an existing book.

5. **DELETE /books**
   - Deletes a book.

### Book Borrowing Service

1. **GET /books**
   - Retrieves a list of all books available for borrowing.

2. **POST /borrow/{id}**
   - Borrows a book by its ID.
   - Throws an error if the book is already borrowed.

3. **POST /return/{id}**
   - Returns a borrowed book by its ID.
   - Throws an error if the book is already returned.

## Eureka Service

The Eureka Service is used for service registration and discovery.

## Requirements

- Java 8 or higher
- Spring Boot
- Maven

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/JaykumarPatel1998/BookManagementService.git
   ```
2. Navigate to the project directory:
   ```bash
   cd BookManagementService
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the Eureka Service:
   ```bash
   cd EurekaService
   mvn spring-boot:run
   ```
5. Run the Book Service:
   ```bash
   cd ../BookService
   mvn spring-boot:run
   ```
6. Run the Book Borrowing Service:
   ```bash
   cd ../BorrowingService
   mvn spring-boot:run
   ```

## Error Handling

- Borrowing a book that is already borrowed will throw an error.
- Returning a book that is already returned will throw an error.

## Contact

For any questions or issues, please contact Jaykumar Patel at [jaykumarpatel2710@gmail.com].

---

This README provides an overview of the project, instructions on how to set it up and run it, details on the available services and their endpoints, and information on error handling.
