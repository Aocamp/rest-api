package com.api.resource;

import com.api.dao.UserDao;
import com.api.model.User;
import com.api.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("users")
public class UserResource {
    UserDao userDao = new UserDao();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> getAllOrders()  {
        return userDao.getAll();
    }

    @GET
    @Path("/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User getUser(@PathParam("userId") Long userId) {
        return userDao.getById(userId);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User addUser(User user){
        return userDao.addUser(user);
    }

    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User updateOrders(User user) {
        return userDao.updateUser(user);
    }

    @DELETE
    @Path("/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteOrders(@PathParam("userId") Long userId) {
        userDao.delete(userId);
    }

}

