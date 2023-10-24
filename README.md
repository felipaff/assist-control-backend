# Welcome to Assist Control App (Backend)

Thank you for choosing Assist Control App! This is the backend of the application that will enable you to efficiently manage employee records, their positions, and contract types. Below, we provide you with key information to get started with using the application.

## Installation and Setup

1. **Prerequisites**:
   - Make sure you have [Java](https://www.java.com/) and [Maven](https://maven.apache.org/) installed on your system.
   - Configure a MySQL database and ensure you have access credentials.

2. **Clone the Repository**:
   Clone the GitHub repository of the Assist Control App backend to your local machine.

   ```bash
   git clone https://github.com/felipaff/assist-control-backend.git

   ```
## Database Configuration

1. Open the project folder in your IDE (for example, IntelliJ IDEA or Eclipse).

2. Configure your database credentials in the `application.properties` file.

## Running the Application

- Run the application from your IDE or using Maven.

## Using the API

You can access the Assist Control App API through the respective routes:

- **Base URL:** `http://localhost:8080/api`

### Endpoint Examples

- **GET /employees:** Get the list of all employees.
- **POST /employees:** Add a new employee.
- **PUT /employees/{id}:** Update employee information.

- **GET /positions:** Get the list of all positions.
- **POST /positions:** Add a new position.

- **GET /contract-types:** Get the list of all contract types.
- **POST /contract-types:** Add a new contract type.

### New Endpoints for Managing HoursWorked:

- **GET /hours-worked/{id}:** Get hours worked records associated with a specific employee.
- **POST /hours-worked:** Add a new hours worked record for an employee.
- **PUT /hours-worked/{id}:** Update hours worked record.
- **DELETE /hours-worked/{id}:** Delete hours worked record.