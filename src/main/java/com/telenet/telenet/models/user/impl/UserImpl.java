package com.telenet.telenet.models.user.impl;

import com.telenet.telenet.models.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data @AllArgsConstructor @RequiredArgsConstructor
public class UserImpl implements User {
    private int id;
    private String name;
    private String login;
    private String password;
}
