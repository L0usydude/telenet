package com.telenet.telenet.springmvc.services;

import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.user.User;
import com.telenet.telenet.utils.database.UserADMINDataBase;
import com.telenet.telenet.utils.database.UserDEFAULT_USERDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Service
public class RegistrationService {
    @Autowired
    UserADMINDataBase userADMINDataBase;
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;

    public String checker(Model model, @RequestParam(name = "login") String login, @RequestParam(name = "password") String password ) throws SQLException {
        if(userDEFAULT_userDataBase.getByLogin(login) == null && userADMINDataBase.getByLogin(login) == null) {
            User newUser = new User(userDEFAULT_userDataBase.getListOfUsers().size() + 1,"name1",login,password, RoleEnum.DEFAULT_USER);
            userDEFAULT_userDataBase.addUser(newUser);
            return "authorization/authorization";
        }
        else {
            return "authorization/authorization";
        }
    }
}
