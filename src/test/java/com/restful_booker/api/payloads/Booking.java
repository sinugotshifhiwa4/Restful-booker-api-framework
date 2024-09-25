package com.restful_booker.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {

    //@JsonProperty("firstname")
    private String firstname;

    //@JsonProperty("lastname")
    private String lastname;

    //@JsonProperty("totalprice")
    private float totalprice;

    //@JsonProperty("depositpaid")
    private boolean depositpaid = true;

    //@JsonProperty("bookingdates")
    private BookingDates bookingdates;

    //@JsonProperty("additionalneeds")
    private String additionalneeds;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }
}
