package com.restful_booker.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates {

    //@JsonProperty("checkin")
    private String checkin;

    //@JsonProperty("checkout")
    private String checkout;


    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}
