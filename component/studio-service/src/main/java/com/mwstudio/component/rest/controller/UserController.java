package com.mwstudio.component.rest.controller;

import com.mwstudio.component.rest.dao.entity.UserEntity;
import com.mwstudio.component.rest.jaxb.pojo.UsersType;
import com.mwstudio.component.rest.service.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mengwang
 * Date: 12/05/2013
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */

@Path("/booking")
public class UserController extends CommonController {
    private UserService userService;

    @GET
    @Path("/list")
    public Response listUsers() {
        UsersType users = userService.listUsers();
        return Response.ok(users).header("Content-Type", "text/html").build();

    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
