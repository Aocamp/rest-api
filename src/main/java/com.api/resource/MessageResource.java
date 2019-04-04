package com.api.resource;

import com.api.dao.MessageDao;
import com.api.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
public class MessageResource {
    MessageDao messageDao = new MessageDao();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getAllMessages()  {
        return messageDao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Message getMessage(@PathParam("id") Long id) {
        return messageDao.getById(id);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByUserId(@PathParam("userId") Long userId) {
        return messageDao.getMessagesByUserId(userId);
    }

    @GET
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByRoomId(@PathParam("roomId") Long roomId) {
        return messageDao.getMessagesByRoomId(roomId);
    }

    @DELETE
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteMessage(@PathParam("id") Long id) {
        messageDao.deleteMessagesInRoom(id);
    }

    @DELETE
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteMessagesInRoom(@PathParam("roomId") Long roomId) {
        messageDao.deleteMessagesInRoom(roomId);
    }

    @DELETE
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUserMessages(@PathParam("userId") Long userId) {
        messageDao.deleteUserMessages(userId);
    }
}
