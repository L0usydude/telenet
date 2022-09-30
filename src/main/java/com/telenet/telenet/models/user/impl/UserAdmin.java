package com.telenet.telenet.models.user.impl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class UserAdmin {
    private int id;
    private String name;
    private String login;
    private String password;
}
