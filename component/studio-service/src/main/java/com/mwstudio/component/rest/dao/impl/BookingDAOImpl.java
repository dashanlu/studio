package com.mwstudio.component.rest.dao.impl;

import com.mwstudio.component.rest.dao.BookingDAO;
import com.mwstudio.component.rest.dao.entity.BookingEntity;

public class BookingDAOImpl extends GenericHibernateDAO<BookingEntity, Integer> implements BookingDAO {
    public BookingDAOImpl() {
        super(BookingEntity.class);
    }

}
