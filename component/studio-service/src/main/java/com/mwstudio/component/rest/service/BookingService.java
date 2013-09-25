package com.mwstudio.component.rest.service;

import com.mwstudio.component.rest.jaxb.pojo.BookingType;
import com.mwstudio.component.rest.jaxb.pojo.BookingsType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeConfigurationException;


public interface BookingService {
    public BookingsType listBookings();

    @Transactional(propagation= Propagation.REQUIRED)
    public void makeBooking(BookingType booking);

    @Transactional(propagation = Propagation.REQUIRED)
    public BookingType updateBooking(BookingType booking)throws DatatypeConfigurationException;

    public void deleteBooking(Integer bookingId);
}
