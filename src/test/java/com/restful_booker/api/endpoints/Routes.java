package com.restful_booker.api.endpoints;

import com.restful_booker.api.utils.ConfigProperties;
import com.restful_booker.api.utils.ReadSecretKey;

import static com.restful_booker.api.utils.EncryptionUtil.decrypt;

public class Routes {


    // Base URL
    private static final String BASE_URL = decrypt(ConfigProperties.getPropertyByKey("base_url"), ReadSecretKey.getSecretKeyByKey("SECRET_KEY"));

    // Authentication - Create Token
    public static final String CREATE_TOKEN_URL = BASE_URL + "/auth";

    // Booking CRUD
    public static final String CREATE_BOOKING_URL = BASE_URL + "/booking";

    public static final String getBookingUrl = BASE_URL + "/booking/{id}";
    public static final String updateBookingUrl = BASE_URL + "/booking/{id}";
    public static final String patchBookingUrl = BASE_URL + "/booking/{id}";
    public static final String deleteBookingUrl = BASE_URL + "/booking/{id}";

}
