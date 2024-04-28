package com.example.project3;


//User class and constructor
public class User extends MainActivity{
    private String user_name;
    private String user_password;

    public  User ( String username, String password) {
        super();
        this.user_name = username;
        this.user_password = password;
    }
    public String getUser_name() {return user_name;}
    public String getUser_password() {
        return user_password;
    }
    public void setUser_name (String username) {
        this.user_name = username;
    }
    public void setUser_password(String password) {
        this.user_password = password;
    }
    //getter and setter methods for accessing objects
}
