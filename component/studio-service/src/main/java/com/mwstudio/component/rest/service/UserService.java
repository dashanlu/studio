package com.mwstudio.component.rest.service;

import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.UsersType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 12/05/2013
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public interface UserService {
    public UsersType listUsers();
}
