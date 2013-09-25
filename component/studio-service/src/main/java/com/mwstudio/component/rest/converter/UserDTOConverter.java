package com.mwstudio.component.rest.converter;

import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.ObjectFactory;
import com.mwstudio.component.rest.jaxb.pojo.UserType;
import com.mwstudio.component.rest.jaxb.pojo.UsersType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 23/09/2013
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class UserDTOConverter {

    private static ObjectFactory objectFactory;

    static {
        objectFactory = new ObjectFactory();
    }

    public static UserType covertFromEntity(UserEntity ue) {
        UserType userType = objectFactory.createUserType();

        userType.setId(BigInteger.valueOf(ue.getId().longValue()));
        userType.setFirstname(ue.getFirstname());
        userType.setSurname(ue.getSurname());

        return userType;

    }

    public static UsersType covertFromEntity(List<UserEntity> ues){
        UsersType usersType = objectFactory.createUsersType();
        List<UserType> users = usersType.getUser();
        for(UserEntity ue : ues)
            users.add(covertFromEntity(ue));

        return usersType;
    }

    public static UserEntity covertToEntity(UserType ut){
        UserEntity ue = new UserEntity();

        ue.setFirstname(ut.getFirstname());
        ue.setSurname(ut.getSurname());
        ue.setId(ut.getId().intValue());

        return ue;
    }

    public static List<UserEntity> covertToEntity(UsersType ust){
        List<UserEntity> ues = new ArrayList<UserEntity>();

        for(UserType ut : ust.getUser())
            ues.add(covertToEntity(ut));

        return ues;
    }
}
