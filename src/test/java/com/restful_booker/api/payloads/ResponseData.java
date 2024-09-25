package com.restful_booker.api.payloads;

import javax.crypto.SecretKey;

public class ResponseData {

    private String token;
    private int bookingId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}
