package com.mwstudio.component.rest.service.impl;

import com.mwstudio.component.rest.dao.BookingDAO;
import com.mwstudio.component.rest.dao.entity.BookingEntity;
import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.BookingsType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 26/08/2013
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class BookingServiceImplTest {
    private static BookingServiceImpl bookingService;
    private static BookingDAO mockBookingDAO;

    @BeforeClass
    public static void setUp() throws Exception {
        mockBookingDAO = createMock(BookingDAO.class);
        bookingService = new BookingServiceImpl(mockBookingDAO);
    }

    @Test
    public void listBookings_shouldListAllBookings(){
        BookingEntity mockBookinEntity = createMock(BookingEntity.class);
        UserEntity mockUserEntity = createMock(UserEntity.class) ;

        expect(mockUserEntity.getId()).andReturn(Integer.valueOf(1));

        expect(mockBookinEntity.getId()).andReturn(Integer.valueOf(1));
        expect(mockBookinEntity.getDate()).andReturn(new Date());
        expect(mockBookinEntity.getUser()).andReturn(mockUserEntity);

        expect(mockBookingDAO.list()).andReturn(Arrays.asList(mockBookinEntity));


        replay(mockBookinEntity, mockUserEntity, mockBookingDAO);

        BookingsType bookings = bookingService.listBookings();

        assertEquals(1, bookings.getBooking().size());
        verify(mockBookinEntity, mockUserEntity, mockBookingDAO);

    }
}
