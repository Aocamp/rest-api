package com.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "roomsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {
    private String id;
    private String roomName;
    private String userId;

    public Room(){

    }

    public Room(String id, String roomName, String userId) {
        this.id = id;
        this.roomName = roomName;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

