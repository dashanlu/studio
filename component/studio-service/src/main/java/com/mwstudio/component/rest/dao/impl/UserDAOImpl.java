package com.mwstudio.component.rest.dao.impl;

import com.mwstudio.component.rest.dao.UserDAO;
import com.mwstudio.component.rest.dao.entity.UserEntity;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 11/05/2013
 * Time: 18:37
 * To change this template use File | Settings | File Templates.
 */
public class UserDAOImpl extends GenericHibernateDAO<UserEntity, Integer> implements UserDAO {
    public UserDAOImpl() {
        super(UserEntity.class);
    }
}
