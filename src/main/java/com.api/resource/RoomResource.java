package com.api.resource;

import com.api.model.Room;
import com.api.service.RoomService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("rooms")
public class RoomResource {
    RoomService roomService = new RoomService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Room> getAllRooms()  {
        return roomService.getAllRooms();
    }

    @GET
    @Path("/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Room getRoom(@PathParam("roomId") Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Room> getRoomsByUserId(@PathParam("userId") Long userId) {
        return roomService.getRoomsByUserId(userId);
    }

    @DELETE
    @Path("/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteRoom(@PathParam("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
    }
}

