package com.mwstudio.component.rest.service.impl;

import com.mwstudio.component.rest.converter.UserDTOConverter;
import com.mwstudio.component.rest.dao.UserDAO;
import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.UsersType;
import com.mwstudio.component.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 12/05/2013
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public UsersType listUsers() {

        return UserDTOConverter.covertFromEntity(userDAO.list());
    }
}
