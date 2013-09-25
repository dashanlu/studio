package com.mwstudio.component.rest.dao;

import com.mwstudio.component.rest.AbstractTest;
import com.mwstudio.component.rest.dao.entity.BookingEntity;
import com.mwstudio.component.rest.dao.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

public class BookingDAOTest extends AbstractTest {
    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private UserEntity candidate;


    @Before
    public void prepareData() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO STUDIO.USER ");
        sb.append("(");
        sb.append(" FIRSTNAME, ");
        sb.append(" SURNAME");
        sb.append(") ");
        sb.append(" VALUES ( ");
        sb.append(" 'Dashan',  ");
        sb.append(" 'Lu' ");
        sb.append(" ) ");

        sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).executeUpdate();


        //reset sb
        sb.delete(0, sb.length());
        sb.append("SELECT * FROM STUDIO.USER");
        List<UserEntity> candidates = sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).addEntity(UserEntity.class).list();

        candidate = candidates.get(0);
    }

    @Test
    public void testBookingDAO() {
        //test insert
        BookingEntity be = new BookingEntity();
        be.setUser(candidate);
        Date date = new Date();
        be.setDate(date);

        bookingDAO.insert(be);

        StringBuilder sb = new StringBuilder("SELECT * FROM STUDIO.BOOKING");
        List<BookingEntity> candidates = sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).addEntity(BookingEntity.class).list();

        Assert.assertEquals(1, candidates.size());
        be = candidates.get(0);
        Assert.assertEquals(date, be.getDate());
        Assert.assertEquals(candidate.getId(), be.getUser().getId());


        //test update
        date = new Date();
        be.setDate(date);

        bookingDAO.update(be);

        candidates = sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).addEntity(BookingEntity.class).list();
        Assert.assertEquals(1, candidates.size());
        be = candidates.get(0);
        Assert.assertEquals(date, be.getDate());
        Assert.assertEquals(candidate.getId(), be.getUser().getId());

        //test delete
        bookingDAO.delete(be);
        candidates = sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).addEntity(BookingEntity.class).list();
        Assert.assertEquals(0, candidates.size());

    }
}
