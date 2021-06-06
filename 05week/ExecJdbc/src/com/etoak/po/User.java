package com.etoak.po;

import java.util.Date;

/**
 *
 */
public class User {

    private Integer id;
    private String name;
    private String pass;
    private Date date;

    public User(){}

    public User(Integer id, String name, String pass, Date date) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User(Integer id, String name, String pass){
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", date=" + date +
                '}';
    }
}
