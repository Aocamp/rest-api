package com.api.resource;

import com.api.model.Message;
import com.api.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
public class MessageResource {
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getAllMessages()  {
        return MessageService.getAllMessages();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Message getMessage(@PathParam("id") String id) {
        return MessageService.getMessageById(id);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByUserId(@PathParam("userId") String userId) {
        return MessageService.getMessagesByUserId(userId);
    }

    @GET
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByRoomId(@PathParam("roomId") String roomId) {
        return MessageService.getMessagesByRoomId(roomId);
    }

    @DELETE
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteMessagesInRoom(@PathParam("roomId") String roomId) {
        MessageService.deleteMessagesInRoom(roomId);
    }

    @DELETE
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUserMessages(@PathParam("userId") String userId) {
        MessageService.deleteUserMessages(userId);
    }
}
