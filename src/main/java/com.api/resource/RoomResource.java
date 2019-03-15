package com.api.resource;

import com.api.model.Room;
import com.api.service.RoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rooms")
public class RoomResource {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Room> getAllRooms()  {
        return RoomService.getAllRooms();
    }

    @GET
    @Path("/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Room getRoom(@PathParam("roomId") String roomId) {
        return RoomService.getRoomById(roomId);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Room getRoomByUserId(@PathParam("userId") String userId) {
        return RoomService.getRoomByUserId(userId);
    }

    @DELETE
    @Path("/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteRoom(@PathParam("roomId") String roomId) {
        RoomService.deleteRoom(roomId);
    }
}

