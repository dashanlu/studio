package com.mwstudio.component.rest.service.impl;


import com.mwstudio.component.rest.converter.BookingDTOConverter;
import com.mwstudio.component.rest.dao.BookingDAO;
import com.mwstudio.component.rest.dao.entity.BookingEntity;
import com.mwstudio.component.rest.jaxb.pojo.BookingType;
import com.mwstudio.component.rest.jaxb.pojo.BookingsType;
import com.mwstudio.component.rest.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.datatype.DatatypeConfigurationException;

public class BookingServiceImpl implements BookingService {
    private BookingDAO bookingDAO;

    @Autowired
    public BookingServiceImpl(BookingDAO bookingDAO){
        this.bookingDAO = bookingDAO;
    }

    @Override
    public BookingsType listBookings() {
        try {
            return BookingDTOConverter.covertFromEntity(bookingDAO.list());
        } catch (DatatypeConfigurationException dce) {
            return null;
        }

    }

    @Override
    public void makeBooking(BookingType booking) {
        bookingDAO.insert(BookingDTOConverter.convertToEntity(booking));
    }

    @Override
    public BookingType updateBooking(BookingType booking) throws DatatypeConfigurationException {
        BookingEntity bookingEntity = bookingDAO.update(BookingDTOConverter.convertToEntity(booking));
        return BookingDTOConverter.covertFromEntity(bookingEntity);
    }

    @Override
    public void deleteBooking(Integer bookingId) {
        bookingDAO.delete(bookingDAO.get(bookingId));
    }

}
