package com.api.resource;

import com.api.dao.MessageDao;
import com.api.model.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("messages")
public class MessageResource extends BaseResource<Message, MessageDao>{
    private MessageDao dao = new MessageDao();

    public MessageResource(){
        setDb(dao);
    }

    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByUserId(@PathParam("userId") Long userId) {
        return dao.getMessagesByUserId(userId);
    }

    @GET
    @Path("/support/{supportId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageBySupportId(@PathParam("supportId") Long supportId) {
        return dao.getMessagesBySupportId(supportId);
    }

    @GET
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<Message> getMessageByRoomId(@PathParam("roomId") Long roomId) {
        return dao.getMessagesByRoomId(roomId);
    }

    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Message addMessage(Message message){
        return dao.addMessage(message);
    }

    @DELETE
    @Path("/room/{roomId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteMessagesInRoom(@PathParam("roomId") Long roomId) {
        dao.deleteMessagesInRoom(roomId);
    }

    @DELETE
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteUserMessages(@PathParam("userId") Long userId) {
        dao.deleteUserMessages(userId);
    }
}
