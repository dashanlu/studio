package com.mwstudio.component.rest.dao;

import com.mwstudio.component.rest.AbstractTest;
import com.mwstudio.component.rest.dao.entity.UserEntity;
import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 11/05/2013
 * Time: 18:42
 * To change this template use File | Settings | File Templates.
 */
public class UserDAOTest extends AbstractTest {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


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

    }

    @Test
    public void userDAOTest() {
        //test list
        List<UserEntity> users = userDAO.list();
        Assert.assertEquals(1, users.size());

        Assert.assertEquals("Dashan", users.get(0).getFirstname());
        Assert.assertEquals("Lu", users.get(0).getSurname());

        //test update
        UserEntity beforeChanged = users.get(0);

        beforeChanged.setFirstname("Meng");
        beforeChanged.setSurname("Wang");

        UserEntity afterChanged = userDAO.update(beforeChanged);

        Assert.assertEquals(beforeChanged.getSurname(), afterChanged.getSurname());
        Assert.assertEquals(beforeChanged.getFirstname(), afterChanged.getFirstname());
        Assert.assertEquals(beforeChanged.getId(), afterChanged.getId());

        //test delete
        userDAO.delete(afterChanged);

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) FROM UserEntity");
        int columnSize = ((Long) sessionFactory.getCurrentSession().createQuery(sb.toString()).uniqueResult()).intValue();
        Assert.assertEquals(0, columnSize);

        //test insert
        UserEntity newCandidate = new UserEntity();
        newCandidate.setSurname("Lu");
        newCandidate.setFirstname("Dashan");

        userDAO.insert(newCandidate);
        sb.delete(0, sb.length());
        sb.append("SELECT * FROM STUDIO.USER");
        List<UserEntity> candidates = sessionFactory.getCurrentSession().createSQLQuery(sb.toString()).addEntity(UserEntity.class).list();

        Assert.assertEquals(1, candidates.size());
        Assert.assertEquals("Dashan", candidates.get(0).getFirstname());
        Assert.assertEquals("Lu", candidates.get(0).getSurname());

    }
}
