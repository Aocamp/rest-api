package com.api.resource;

import com.api.model.Message;
import com.api.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
public class MessageResource {
    MessageService messageService = new MessageService();
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getAllMessages()  {
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Message getMessage(@PathParam("id") Long id) {
        return messageService.getMessageById(id);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByUserId(@PathParam("userId") Long userId) {
        return messageService.getMessagesByUserId(userId);
    }

    @GET
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByRoomId(@PathParam("roomId") Long roomId) {
        return messageService.getMessagesByRoomId(roomId);
    }

    @DELETE
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteMessagesInRoom(@PathParam("roomId") Long roomId) {
        messageService.deleteMessagesInRoom(roomId);
    }

    @DELETE
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUserMessages(@PathParam("userId") Long userId) {
        messageService.deleteUserMessages(userId);
    }
}
