Here's the updated `README.md` with the revised instructions for running tests:

---

# Restful-Booker API Automation Framework

This is an API Automation Framework for testing the Restful-Booker API. The framework is built using **Java**, **RestAssured**, and **AES encryption** for secure credential management. It includes configuration management, payload handling, logging, encryption, and CRUD test automation.

## Project Structure

```
src
│
├── main
│   └── java
│       └── endpoints
│           ├── BookingEndpoints.java
│           └── Routes.java
│       └── payloads
│           ├── Booking.java
│           ├── Credentials.java
│           └── ResponseData.java
│       └── utils
│           ├── ConfigLogger.java
│           ├── ConfigProperties.java
│           ├── EncryptionUtil.java
│           └── EncryptCredentials.java
│   └── resources
│       ├── config.properties
│       └── log4j2.xml
└── test
    └── java
        └── tests
            ├── CreateBookingTest.java
            ├── GetBookingTest.java
            ├── UpdateBookingTest.java
            ├── PatchBookingTest.java
            └── DeleteBookingTest.java
```

## Features

- **Endpoints**: 
  - `Routes.java`: Contains all API endpoint URLs.
  - `BookingEndpoints.java`: Implements all CRUD operations (create, retrieve, update, delete) and returns `Response` objects.

- **Payloads**: 
  - `Booking.java`: POJO for booking details, with getters and setters.
  - `Credentials.java`: POJO for username and password.
  - `ResponseData.java`: Stores dynamic values like `bookingId` and `token` for use during execution.

- **Utilities**:
  - `ConfigLogger.java`: Sets up logging using Log4j2 (`log4j2.xml`).
  - `ConfigProperties.java`: Loads and reads from `config.properties`.
  - `EncryptionUtil.java`: Generates AES key and contains methods for encryption and decryption.
  - `EncryptCredentials.java`: Encrypts credentials (username and password) and writes the secret key to `secretKey.properties`.

- **Test Base**:
  - `TestBase.java`: Initializes payloads, endpoints, and test data using `Booking`, `BookingEndpoints`, `Faker`, `CredentialsPayload`, and `ResponseData`.

- **Tests**: 
  - Contains CRUD operation tests including creating a token, creating, retrieving, updating, patching, and deleting a booking.

## Configuration

### 1. `config.properties`
You need to create a `config.properties` file under `src/main/resources/` with the following keys:
- `base_url`
- `username`
- `password`

### 2. Encrypting Credentials
Run the `EncryptCredentials` class to encrypt the `username` and `password`. This will generate the `secretKey.properties` file, which is necessary for decryption during execution.

**Steps**:
1. Ensure all credentials are initialized in `config.properties`.
2. Run the `EncryptCredentials` class to encrypt credentials and generate `secretKey.properties`.

### 3. `secretKey.properties`
This file is auto-generated when the encryption process is completed. It contains the AES encryption key used to encrypt and decrypt sensitive information.

## Dependencies

- **RestAssured**: For REST API testing.
- **JUnit/TestNG**: Test framework for test execution.
- **Surefire Plugin**: For test execution during the build process.
- **Maven**: For project management and dependency handling.

### Maven Profiles

The project includes a profile for running tests with the `mvn clean test` command. 

## How to Run Tests

1. Clone the repository.
2. Create the necessary configuration files (`config.properties`).
3. Encrypt your credentials by running the `EncryptCredentials` class.
4. Run tests using the command:
   ```bash
   mvn clean test -Puat -DEnvironment=uat
   ```

Tests cover:
- Token creation
- Booking creation
- Get booking by ID
- Update booking by ID
- Patch booking by ID
- Delete booking by ID

## Logging

The framework uses Log4j2 for logging. Configuration is handled through `log4j2.xml`. Logs will be generated during test execution.

---

Let me know if you need any further modifications!
