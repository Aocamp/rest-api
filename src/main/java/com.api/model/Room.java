package com.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "roomsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Room {
    private Long id;
    private String roomName;
    private Long userId;

    public Room(){

    }

    public Room(Long id, String roomName, Long userId) {
        this.id = id;
        this.roomName = roomName;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

