package com.telenet.telenet.models.user;

public interface User {
    String getName();
    int getId();
    String getLogin();
    String getPassword();
    void setPassword(String newPassword);
    void setId(int newId);
    void setName(String newName);
    void setLogin(String newLogin);
}
