package com.mwstudio.component.rest;

import com.mwstudio.component.rest.dao.HibernateDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 07/04/2013
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class DBTestUtils<T> implements HibernateDAO, ApplicationContextAware {

    private Class<T> persistedClass;
    private ApplicationContext applicationContext;

    public DBTestUtils(Class<T> clazz) {
        persistedClass = clazz;
    }

    //it should be declared in HibernateDAO interface
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    protected Criteria createCriteria(Class<?> clazz) {
        return sessionFactory.getCurrentSession().createCriteria(clazz);
    }


    protected Query createQuery(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql);
    }

    protected Query createSQLQuery(String sql) {
        return sessionFactory.getCurrentSession().createSQLQuery(sql);
    }

    public void runSQLQuery(String query) {
        createSQLQuery(query).executeUpdate();
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

