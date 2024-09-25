package com.restful_booker.api.utils;

import com.github.javafaker.Faker;
import com.restful_booker.api.endpoints.BookingEndpoints;
import com.restful_booker.api.payloads.Booking;
import com.restful_booker.api.payloads.BookingDates;
import com.restful_booker.api.payloads.Credentials;
import com.restful_booker.api.payloads.ResponseData;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.restful_booker.api.utils.EncryptionUtil.decrypt;

public class TestBase {

    protected Booking bookingPayload;
    protected BookingEndpoints bookingEndpoints;
    protected Faker faker;
    protected Credentials credentialsPayload;
    //protected ResponseData responseData;
    protected static final Logger logger = ConfigLogger.getLogger();


    /* @BeforeClass Initialization (Not Ideal for Parallel Tests)
    Class-Level Scope: @BeforeClass is executed once for each class, not for each instance of the class.
    If you’re running tests in parallel, this means all the test methods in the class will share the same instance of
    ResponseData. This can lead to shared state problems, where one test can accidentally modify the state for another,
    causing flaky or unpredictable results.
    Shared State: Since @BeforeClass initialization is class-scoped, it's not ideal for test isolation in parallel tests.
    Multiple test methods might concurrently modify the same ResponseData object, which could lead to race conditions or
     incorrect results.
     */

    //    @BeforeClass
    //    public void initResponseData(){
    //        responseData = new ResponseData();
    //    }

    /**
     * Initializes the test setup by creating instances of necessary classes and setting up data for booking.
     */
    @BeforeMethod
    public void init() {
        try {
            // Initialize Faker for generating mock data
            faker = new Faker();

            // Create a new Booking object
            bookingPayload = new Booking();

            // Create a new BookingEndpoints object for API interactions
            bookingEndpoints = new BookingEndpoints();

            // Create a new Auth Create Token Object for API
            credentialsPayload = new Credentials();

            // Set Auth
            credentialsPayload.setUsername(decrypt(ConfigProperties.getPropertyByKey("username"),  ReadSecretKey.getSecretKeyByKey("SECRET_KEY")));
            credentialsPayload.setPassword(decrypt(ConfigProperties.getPropertyByKey("password"),  ReadSecretKey.getSecretKeyByKey("SECRET_KEY")));

            // Set up mock data for booking payload
            bookingPayload.setFirstname(faker.name().firstName());
            bookingPayload.setLastname(faker.name().lastName());
            bookingPayload.setTotalprice(Float.parseFloat(faker.commerce().price().replace(",", ".")));

            // Initialize BookingDates object
            BookingDates bookingDates = new BookingDates();

            // Get the current date and format it
            LocalDate currentDay = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedCurrentDate = currentDay.format(dateFormatter);

            // Calculate and format the future date (30 days from now)
            LocalDate futureDate = currentDay.plusDays(30);
            String formattedFutureDate = futureDate.format(dateFormatter);

            // Set booking dates in the BookingDates object
            bookingDates.setCheckin(formattedCurrentDate);
            bookingDates.setCheckout(formattedFutureDate);

            // Assign the BookingDates object to the booking payload
            bookingPayload.setBookingdates(bookingDates);

            // Set additional needs for the booking
            bookingPayload.setAdditionalneeds("Breakfast");

            logger.info("Init method executed successfully");

        } catch (Exception e) {
            logger.error("Error initializing test base setup: {}", e.getMessage(), e);
            throw new RuntimeException("Error initializing test base setup: " + e.getMessage(), e);
        }
    }
}
