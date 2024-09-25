Got it! I'll update the `README.md` to include the correct command for running tests with the `uat` profile and environment.

Here's the updated `README.md`:

```markdown
# Restful-Booker API Automation Framework

This project is an automation framework for testing the Restful-Booker API. It uses Java, RestAssured, and other dependencies to perform CRUD operations and validate the API responses.

## Project Structure

- **src/main/resources**
  - `config.properties`: Contains `base_url`, `username`, and `password`.
  - `secretKey.properties`: Generated when you run the encryption class.

- **src/main/java/com/restful_booker/api**
  - **endpoints**
    - `Routes.java`: Contains all URLs.
    - `Endpoints.java`: Contains all CRUD endpoints that return responses.
  - **payloads**
    - `Booking.java`: POJO with setters and getters for booking data.
    - `Credentials.java`: POJO for username and password.
    - `ResponseData.java`: Stores `bookingId` and `token` during execution.
  - **tests**
    - Contains all test classes with CRUD operations.
  - **utils**
    - `ConfigLogger.java`: Logger class with `log4j2.xml` for logging.
    - `ConfigProperties.java`: Loads and reads properties files.
    - `EncryptionUtil.java`: Generates AES key and contains encrypt/decrypt methods.
    - `EncryptCredentials.java`: Encrypts data.
    - `ReadSecretKey.java`: Reads the secret key.
    - `WriteToFile.java`: Writes the secret key to a file.
    - `TestBase.java`: Initializes test data and runs before tests.

## Setup

1. **Create `config.properties`**:
   - Under `src/main/resources`, create a file named `config.properties` with the following content:
     ```properties
     base_url=<your_base_url>
     username=<your_username>
     password=<your_password>
     ```

2. **Encrypt Credentials**:
   - Run the `EncryptCredentials` class to encrypt the credentials. Ensure all variables to encrypt are included in the class.
   - This will generate `secretKey.properties` under the same location.

## Dependencies

- **Java**
- **RestAssured**
- **Maven Surefire Plugin**
- **Other RestAssured dependencies**

## Profiles

- **uat**: To run tests with the `uat` profile, use the following command:
  ```sh
  mvn clean test -Puat -DEnvironment=uat
  ```

## Logging

- **log4j2.xml**: Located in `src/main/resources` for logging configuration.

## Tests

- **Field-Level Initialization**: Better for parallel tests.
- **CRUD Tests**:
  - Create token
  - Create booking
  - Get booking by ID
  - Update booking by ID
  - Patch booking by ID
  - Delete booking by ID

## Example Initialization in `TestBase` Class

```java
protected Booking bookingPayload;
protected BookingEndpoints bookingEndpoints;
protected Faker faker;
protected Credentials credentialsPayload;
// protected ResponseData responseData;
protected static final Logger logger = ConfigLogger.getLogger();
```

## Running Tests

To run the tests, use the following Maven command:
```sh
mvn clean test -Puat -DEnvironment=uat
```

## Contributing

Feel free to fork this repository and contribute by submitting pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License.
```

This should now include the correct command for running tests with the `uat` profile and environment. If you need any further adjustments or additional information, feel free to let me know!
