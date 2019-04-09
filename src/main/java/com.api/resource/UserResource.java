package com.api.resource;

import com.api.dao.UserDao;
import com.api.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("users")
public class UserResource extends BaseResource<User, UserDao>{
    private UserDao dao = new UserDao();

    public UserResource(){
        setDb(dao);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User addUser(User user){
        return dao.addUser(user);
    }

    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User updateOrders(User user) {
        return dao.updateUser(user);
    }
}

