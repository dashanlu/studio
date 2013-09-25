package com.mwstudio.component.rest.service.impl;

import com.mwstudio.component.rest.dao.UserDAO;
import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.UsersType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 26/08/2013
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceImplTest {
    private static UserServiceImpl userService;
    private static UserDAO mockUserDAO;

    @BeforeClass
    public static void setUp() {
        mockUserDAO = createMock(UserDAO.class);
        userService = new UserServiceImpl(mockUserDAO);

    }

    @Test
    public void listBookings_shouldListAllBookings() {
        UserEntity mockUserEntyty = createMock(UserEntity.class);
        expect(mockUserEntyty.getId()).andReturn(Integer.valueOf(1));
        expect(mockUserEntyty.getFirstname()).andReturn("FirstName");
        expect(mockUserEntyty.getSurname()).andReturn("Surname");
        expect(mockUserDAO.list()).andReturn(Arrays.asList(mockUserEntyty));

        replay(mockUserDAO, mockUserEntyty);

        UsersType users = userService.listUsers();

        assertEquals(1, users.getUser().size());
        verify(mockUserDAO, mockUserEntyty);
    }
}
