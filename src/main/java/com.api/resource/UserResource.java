package com.api.resource;

import com.api.model.User;
import com.api.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("users")
public class UserResource {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> getAllOrders()  {
        return UserService.getAllUsers();
    }

    @GET
    @Path("/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User getUser(@PathParam("userId") String userId) {
        return UserService.getUserById(userId);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User addUser(User user){
        return UserService.addUser(user);
    }

    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User updateOrders(User user) {
        return UserService.updateUser(user);
    }

    @DELETE
    @Path("/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteOrders(@PathParam("userId") String userId) {
        UserService.deleteUser(userId);
    }

}

