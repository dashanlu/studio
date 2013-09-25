package com.mwstudio.component.mvc.controller;

import com.mwstudio.component.rest.jaxb.pojo.BookingType;
import com.mwstudio.component.rest.jaxb.pojo.BookingsType;
import com.mwstudio.component.rest.jaxb.pojo.ObjectFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;


/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 01/06/2013
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */


@Controller
public class BookingController {

    private static final String LISTING = "listing";
    @RequestMapping(value="/bookings", method = RequestMethod.GET)
    public String listBookings(ModelMap model){
        ObjectFactory factory = new ObjectFactory();
        BookingType booking = factory.createBookingType();
        BookingsType bookings = factory.createBookingsType();

        booking.setUserId(BigInteger.ONE);
        booking.setId(BigInteger.ONE);

        bookings.getBooking().add(booking);

        model.addAttribute("bookings", bookings);

        return LISTING;
    }
}
