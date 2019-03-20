package com.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "messagesList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    private Long id;
    private Long roomId;
    private String messageText;
    private Date messageDate;
    private Long userId;

    public Message(){

    }

    public Message(Long id, Long roomId, String messageText, Date messageDate, Long userId) {
        this.id = id;
        this.roomId = roomId;
        this.messageText = messageText;
        this.messageDate = messageDate;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
