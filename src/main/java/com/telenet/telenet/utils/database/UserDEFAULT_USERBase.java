package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.user.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDEFAULT_USERBase {
    static DatabaseConsts consts;

    public List<User> listOfUsers(ResultSet resultSet) throws SQLException {
        List<User> listOfUsers = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setLogin(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setRole(RoleEnum.DEFAULT_USER);
            listOfUsers.add(user);
        }
        return listOfUsers;
    }

    public List<User> getListOfUsers() throws SQLException {
        String query = "select * from \"UserDEFAULT_USER\";" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }
    public User getUserDEFAULT_USER(int id) throws SQLException {
        String query = "select * from \"UserDEFAULT_USER\" Where id = ?;" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet).get(0);
    }
    public List<User> loginSearch(String login) throws SQLException {
        String query = "SELECT * from \"UserDEFAULT_USER\" where login LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + login + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public List<User> idSearch(String id) throws SQLException {
        String query = "SELECT * from \"UserDEFAULT_USER\" where id = ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public List<User> nameSearch(String name) throws SQLException {
        String query = "SELECT * from \"UserDEFAULT_USER\" where name LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public List<User> passwordSearch(String password) throws SQLException {
        String query = "SELECT * from \"UserDEFAULT_USER\" where password LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + password + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public void updateByLogin(String prevLogin, String newLogin, String newName, String newPassword) throws SQLException {
        String query = "UPDATE \"UserDEFAULT_USER\" SET login = ?, \"name\" = ?, \"password\" = ? where login = ?;";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1,newLogin);
        statement.setString(2,newName);
        statement.setString(3,newPassword);
        statement.setString(4,prevLogin);
        statement.executeUpdate();
    }



}
