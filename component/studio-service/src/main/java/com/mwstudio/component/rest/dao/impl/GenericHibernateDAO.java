package com.mwstudio.component.rest.dao.impl;

import com.mwstudio.component.rest.dao.GenericDAO;
import com.mwstudio.component.rest.dao.HibernateDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Serializable;
import java.util.List;

//miss HibernateDAO

/**
 * @param <T>
 * @param <ID>
 * @author mengwang
 */
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements
        GenericDAO<T, ID>, HibernateDAO {

    private Class<T> persistentClass;

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    //constructor
    public GenericHibernateDAO(Class<T> c) {
        persistentClass = c;
    }


    protected Criteria createCriteria(Class<?> clazz) {
        return sessionFactory.getCurrentSession().createCriteria(clazz);
    }


    protected Query createQuery(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql);
    }

    protected Query createSQLQuery(String sql) {
        return sessionFactory.getCurrentSession().createSQLQuery(sql);
    }


    public void insert(final T entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public void insert(final List<T> entities) {
        for (T entity : entities) {
            this.insert(entity);
        }
    }

    public T update(final T entity) {
        return (T) sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    public void delete(final ID id) {
        T entity = get(id);
        if (entity != null)
            sessionFactory.getCurrentSession().delete(entity);
    }

    public T get(final ID id) {
        return (T) sessionFactory.getCurrentSession().get(persistentClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> list() {
        return createCriteria(persistentClass).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> list(final int startIndex, final int fetchSize) {
        return createCriteria(persistentClass).setFirstResult(startIndex).setFetchSize(fetchSize).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleInstance, String... excludeProperties) {
        Criteria criteria = createCriteria(persistentClass);
        Example example = Example.create(exampleInstance);
        for (String excludeProperty : excludeProperties) {
            example.excludeProperty(excludeProperty);
        }
        criteria.add(example);
        return criteria.list();
    }

    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }
}

