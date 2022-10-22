package com.telenet.telenet.models.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.telenet.telenet.models.BaseEntity;
import com.telenet.telenet.models.enums.roles.RoleEnum;
import lombok.*;


@Getter
@Setter
@Builder
@ToString @AllArgsConstructor @RequiredArgsConstructor
@JsonTypeName("User") @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public class User extends BaseEntity {
    private String name;
    private String login;
    private String password;
    private RoleEnum role;

    public User(int id, String name, String login, String password, RoleEnum role) {
        super(id);
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
