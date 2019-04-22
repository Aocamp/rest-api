package com.api.resource;

import com.api.dao.RoomDao;
import com.api.model.Room;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rooms")
public class RoomResource extends BaseResource<Room, RoomDao>{
    private RoomDao dao = new RoomDao();

    public RoomResource(){
        setDb(dao);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Room getRoomByUserId(@PathParam("userId") Long userId) {
        return dao.getRoomByUserId(userId);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Room addRoom(Room room){
        return dao.addRoom(room);
    }
}

