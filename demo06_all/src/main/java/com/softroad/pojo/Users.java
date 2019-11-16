package com.softroad.pojo;

import java.io.Serializable;

public class Users implements Serializable {

    private static final long serialVersionUID = 7414932024143299903L;


    private Integer userid;

    private String username;

    private Integer userage;

    private Integer iphone;

    private String addree;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getUserage() {
        return userage;
    }

    public void setUserage(Integer userage) {
        this.userage = userage;
    }

    public Integer getIphone() {
        return iphone;
    }

    public void setIphone(Integer iphone) {
        this.iphone = iphone;
    }

    public String getAddree() {
        return addree;
    }

    public void setAddree(String addree) {
        this.addree = addree == null ? null : addree.trim();
    }
}