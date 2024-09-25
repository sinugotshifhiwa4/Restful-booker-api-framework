package com.restful_booker.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful_booker.api.payloads.Booking;
import com.restful_booker.api.payloads.BookingDates;
import com.restful_booker.api.payloads.ResponseData;
import com.restful_booker.api.utils.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingTest extends TestBase {


    /* Field-Level Initialization (Better for Parallel Tests)
    Per-Instance Initialization: When you initialize the object at the field level, every instance of the test class
    gets its own copy of the ResponseData object. This means each test has a completely isolated instance of ResponseData,
    preventing interference between tests, which is crucial in parallel execution.
    Test Isolation: Each test thread will get its own instance of the test class (and consequently, its own ResponseData),
    ensuring that the state of one test does not affect the state of another. This is exactly what you need when running
    tests in parallel.
    Cleaner and Simpler: For parallel tests, field-level initialization is not only simpler but also ensures that each
    test method gets a clean instance of the object without you having to worry about managing this explicitly.
     */
    protected ResponseData responseData = new ResponseData();

    @Test(priority = 1)
    public void testCreateToken() {
        try {

            // Call the createToken method on the bookingEndpoints to obtain a token using the provided credentials
            Response response = this.bookingEndpoints.createToken(this.credentialsPayload);

            // Uncomment the following line to log the full response for debugging purposes
            //response.then().log().all();

            // Assert that the response status code is 200 (OK), indicating the token was created successfully
            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200, "Expected status code 200 but received: " + statusCode);

            // Log the status code to indicate success
            logger.info("Token created successfully. Status code: {}", statusCode);

            // Retrieve the token from the JSON response using JsonPath
            String token = response.jsonPath().getString("token");
            //System.out.println("Token Generated: " + token);

            // Set the extracted token into the bookingPayload object using its setter method
            responseData.setToken(token);

            logger.info("Create token was run successfully and token saved to variable");

        } catch (Exception e) {
            logger.error("Error while running create token method: {}", e.getMessage(), e);
            throw new RuntimeException("Error while running create token method: " + e.getMessage(), e);
        }
    }


    @Test(priority = 2)
    public void testCreateBooking() {

        try {

            Response response = this.bookingEndpoints.createBooking(this.bookingPayload);
            //response.then().log().all();

            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200, "Expected status code 200 but received: " + statusCode);

            // Log the status code to indicate success
            logger.info("Booking created successfully. Status code: {}", statusCode);

            int bookingId = Integer.parseInt(response.jsonPath().getString("bookingid"));
            responseData.setBookingId(bookingId);
            logger.info("Created Id : {}", responseData.getBookingId());

            logger.info("Create booking was run successfully and booking Id saved to variable");

        } catch (Exception e) {
            logger.error("Error while running create booking method: {}", e.getMessage(), e);
            throw new RuntimeException("Error while running create booking method: " + e.getMessage(), e);
        }
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBooking")
    public void testGetBookingById() {
        try {

            // Ensure the booking ID is valid (greater than 0)
            Assert.assertTrue(this.responseData.getBookingId() > 0, "Booking ID should be greater than 0");

            Response response = this.bookingEndpoints.getBookingById(responseData.getBookingId());
            //response.then().log().all(); // Log full response for debugging

            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200, "Expected status code 200 but received: " + statusCode);
            logger.info("Booking retrieved successfully. Status code: {}", statusCode);

            logger.info("Getting booking by Id was executed successfully");

        } catch (Exception e) {
            logger.error("Error while running get booking method: {}", e.getMessage(), e);;
            throw new RuntimeException("Error while running get booking method: " + e.getMessage(), e);
        }
    }

    @Test( priority = 4, dependsOnMethods = "testGetBookingById")
    public void testUpdateBookingById() {

        try {
            // Update data (Make sure to include all required fields)
            bookingPayload = new Booking();
            bookingPayload.setFirstname("Tshifhiwa"); // Keep the original or set a new first name
            bookingPayload.setLastname("Sinugo"); // Update the last name
            bookingPayload.setTotalprice(150); // Set total price
            bookingPayload.setDepositpaid(false); // Update deposit status
            bookingPayload.setAdditionalneeds("Extra Towels"); // Update additional needs

            // Create and set booking dates
            BookingDates bookingDates = new BookingDates();
            bookingDates.setCheckin("2024-09-30"); // Ensure these dates are valid
            bookingDates.setCheckout("2024-10-05");
            bookingPayload.setBookingdates(bookingDates); // Set the booking dates

            Response response = this.bookingEndpoints.updateBookingById(responseData.getBookingId(), responseData.getToken(), this.bookingPayload);
            //response.then().log().all(); // Log full response for debugging

            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200, "Expected status code 200 but received: " + statusCode);
            logger.info("Booking updated successfully. Status code: {}", statusCode);

            logger.info("Updating booking by Id was executed successfully");

        } catch (Exception e) {
            throw new RuntimeException("Error while running update booking method: " + e.getMessage(), e);
        }
    }

    @Test( priority = 5, dependsOnMethods = "testGetBookingById")
    public void testPatchBookingById() {

        try {

            // Update data: Update firstname, but add all fields with same data (if you only send firstname, all other fields will be null)
            bookingPayload = new Booking();
            bookingPayload.setFirstname("Lunah");
            bookingPayload.setLastname("Sinugo");
            bookingPayload.setTotalprice(150);
            bookingPayload.setDepositpaid(false);
            bookingPayload.setAdditionalneeds("Extra Towels");

            // Create and set booking dates
            BookingDates bookingDates = new BookingDates();
            bookingDates.setCheckin("2024-09-30");
            bookingDates.setCheckout("2024-10-05");
            bookingPayload.setBookingdates(bookingDates);

            Response response = this.bookingEndpoints.patchBookingById(responseData.getBookingId(), responseData.getToken(), this.bookingPayload);
            //response.then().log().all(); // Log full response for debugging

            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 200, "Expected status code 200 but received: " + statusCode);
            logger.info("Booking patched successfully. Status code: {}", statusCode);

            logger.info("Patching booking by Id was executed successfully");

        } catch (Exception e) {
            logger.error("Error while running patch booking method: {}", e.getMessage(), e);
            throw new RuntimeException("Error while running patch booking method: " + e.getMessage(), e);
        }
    }

    @Test( priority = 6, dependsOnMethods = "testGetBookingById")
    public void testDeleteBookingById() {

        try {
            Response response = this.bookingEndpoints.deleteBookingById(responseData.getBookingId(), responseData.getToken());
            //response.then().log().all(); // Log full response for debugging

            int statusCode = response.statusCode();
            Assert.assertEquals(statusCode, 201, "Expected status code 201 but received: " + statusCode);
            logger.info("Booking deleted successfully. Status code: {}", statusCode);

            logger.info("Deleting booking by Id was executed successfully");

        } catch (Exception e) {
            logger.error("Error while running deleting booking method: {}", e.getMessage(), e);;
            throw new RuntimeException("Error while running deleting booking method: " + e.getMessage(), e);
        }
    }

}
