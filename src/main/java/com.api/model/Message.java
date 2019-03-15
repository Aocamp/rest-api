package com.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "messagesList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    private String id;
    private String roomId;
    private String messageText;
    private Date messageDate;
    private String userId;

    public Message(){

    }

    public Message(String id, String roomId, String messageText, Date messageDate, String userId) {
        this.id = id;
        this.roomId = roomId;
        this.messageText = messageText;
        this.messageDate = messageDate;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
