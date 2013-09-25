package com.mwstudio.component.rest.controller;


import com.mwstudio.component.rest.jaxb.pojo.BookingType;
import com.mwstudio.component.rest.jaxb.pojo.BookingsType;
import com.mwstudio.component.rest.service.BookingService;
import org.springframework.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.datatype.DatatypeConfigurationException;

import static org.springframework.http.HttpStatus.*;

@Path("/booking")
public class BookingController extends CommonController {

    private BookingService bookingService;

    @GET
    @Path("/list")
    @Produces("application/xml")
    public Response listBookings() {

        BookingsType bookings = bookingService.listBookings();

        if (bookings == null) {
            return Response.serverError().header("Content-Type", "application/xml").build();
        } else {
            return Response.ok(bookings).header("Content-Type", "application/xml").build();
        }


    }

    @POST
    @Path("/make")
    public Response makeBooking(BookingType newBooking){
        bookingService.makeBooking(newBooking);
        return Response.status(HttpStatus.ACCEPTED.value()).build();
    }

    @PUT
    @Path("/update")
    @Produces("application/xml")
    public Response updateBooking(BookingType updateBooking){
        try {
            return Response.ok(bookingService.updateBooking(updateBooking))
                    .header("Content-Type", "application/xml")
                    .build();
        } catch (DatatypeConfigurationException e) {
            return Response.serverError().header("Content-Type", "application/xml").build();
        }
    }

    @DELETE
    @Path("/delete/{bookingId}")
    public Response deleteBooking(@PathParam("bookingId") Integer bookingId){
        bookingService.deleteBooking(bookingId);
        return Response.ok().build();
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    public void setBookingService(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
