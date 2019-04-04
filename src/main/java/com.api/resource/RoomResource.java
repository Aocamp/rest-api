package com.api.resource;

import com.api.dao.RoomDao;
import com.api.model.Room;
import com.api.service.RoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rooms")
public class RoomResource {
    RoomDao roomDao = new RoomDao();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Room> getAllRooms()  {
        return roomDao.getAll();
    }

    @GET
    @Path("/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Room getRoomById(@PathParam("roomId") Long roomId) {
        return roomDao.getById(roomId);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Room> getRoomsByUserId(@PathParam("userId") Long userId) {
        return roomDao.getRoomsByUserId(userId);
    }

    @DELETE
    @Path("/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteRoom(@PathParam("roomId") Long roomId) {
        roomDao.delete(roomId);
    }
}

