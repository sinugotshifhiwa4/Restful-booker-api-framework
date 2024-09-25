package com.restful_booker.api.endpoints;

import com.restful_booker.api.payloads.Booking;
import com.restful_booker.api.payloads.Credentials;
import com.restful_booker.api.payloads.ResponseData;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BookingEndpoints {

    public Response createToken(Credentials payload) {
        try {

            return given()
                    .accept("application/json")
                    .contentType("application/json")
                    .body(payload)
                    .when()
                    .post(Routes.CREATE_TOKEN_URL);
        } catch (Exception e) {
            throw new RuntimeException("Error while setting up create token endpoint: " + e.getMessage(), e);
        }
    }

    public Response createBooking(Booking payload) {
        try {
            return given()
                    .accept("application/json")
                    .contentType("application/json")
                    .body(payload)
                    .when()
                    .post(Routes.CREATE_BOOKING_URL);
        } catch (Exception e) {
            throw new RuntimeException("Error while setting up create booking endpoint: " + e.getMessage(), e);
        }
    }

    public Response getBookingById(int bookingId) {
        try {
            return given()
                    .pathParam("id", bookingId)
                    .when()
                    .get(Routes.getBookingUrl);
        } catch (Exception e) {
            throw new RuntimeException("Error while setting up get booking endpoint: " + e.getMessage(), e);
        }
    }

    public Response updateBookingById(int bookingId, String token, Booking payload) {
        try {
            return given()
                    .accept("application/json")
                    .contentType("application/json")
                    .header("Cookie", "token=" + token)
                    .pathParam("id", bookingId)
                    .body(payload)
                    .when()
                    .put(Routes.updateBookingUrl);
        } catch (Exception e) {
            throw new RuntimeException("Error while setting up update booking endpoint: " + e.getMessage(), e);
        }
    }

    public Response patchBookingById(int bookingId, String token, Booking payload) {
        try {
            return given()
                    .accept("application/json")
                    .contentType("application/json")
                    .header("Cookie", "token=" + token)
                    .pathParam("id", bookingId)
                    .body(payload)
                    .when()
                    .patch(Routes.patchBookingUrl);
        } catch (Exception e) {
            throw new RuntimeException("Error while setting up patch booking endpoint: " + e.getMessage(), e);
        }
    }

    public Response deleteBookingById(int bookingId, String token) {
        try {
            return given()
                    .header("Cookie", "token=" + token)
                    .pathParam("id", bookingId)
                    .when()
                    .delete(Routes.deleteBookingUrl);
        } catch (Exception e) {
            throw new RuntimeException("Error while setting up delete booking endpoint: " + e.getMessage(), e);
        }
    }
}
