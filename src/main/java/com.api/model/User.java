package com.api.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "usersList")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    private String id;
    private String userLogin;

    public User(){

    }

    public User(String id, String userLogin) {
        this.id = id;
        this.userLogin = userLogin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
