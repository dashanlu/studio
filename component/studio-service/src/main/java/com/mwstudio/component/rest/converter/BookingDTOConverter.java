package com.mwstudio.component.rest.converter;

import com.mwstudio.component.rest.dao.entity.BookingEntity;
import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.BookingType;
import com.mwstudio.component.rest.jaxb.pojo.BookingsType;
import com.mwstudio.component.rest.jaxb.pojo.ObjectFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 12/05/2013
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class BookingDTOConverter {
    /**
     * convert the hibernate POJO to JAXB POJO
     *
     * @param be
     * @return BookingTye
     * @throws DatatypeConfigurationException
     */
    private static ObjectFactory objectFactory;
    static{
        objectFactory = new ObjectFactory();
    }


    public static BookingType covertFromEntity(BookingEntity be) throws DatatypeConfigurationException {
        BookingType bt = objectFactory.createBookingType();

        bt.setId(new BigInteger(be.getId().toString()));
        GregorianCalendar gregory = new GregorianCalendar();
        gregory.setTime(be.getDate());

        bt.setDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregory));

        bt.setUserId(new BigInteger(be.getUser().getId().toString()));

        return bt;
    }

    /**
     * convert the JAXB POJO to hibernate POJO
     *
     * @param bt
     * @return BookingEntity
     */
    public static BookingEntity convertToEntity(BookingType bt) {
        BookingEntity be = new BookingEntity();
        be.setId(bt.getId().intValue());
        GregorianCalendar c = bt.getDate().toGregorianCalendar();
        be.setDate(c.getTime());

        UserEntity ue = new UserEntity();
        ue.setId(bt.getId().intValue());
        be.setUser(ue);
        return be;
    }

    /**
     * @param bes
     * @return BookingType
     * @throws DatatypeConfigurationException
     */
    public static BookingsType covertFromEntity(List<BookingEntity> bes) throws DatatypeConfigurationException {
        BookingsType bst = objectFactory.createBookingsType();
        for (BookingEntity be : bes) {
            bst.getBooking().add(covertFromEntity(be));
        }
        return bst;
    }

    /**
     * @param bst
     * @return a list of BookingEntity
     */
    public static List<BookingEntity> convertToEntity(BookingsType bst) {

        List<BookingEntity> bes = new LinkedList<BookingEntity>();
        List<BookingType> bts = bst.getBooking();
        for (BookingType bt : bts) {
            bes.add(convertToEntity(bt));
        }
        return bes;
    }
}
