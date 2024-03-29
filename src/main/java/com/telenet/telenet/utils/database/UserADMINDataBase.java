package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.user.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserADMINDataBase {
    static DataBaseConsts consts;

    public List<User> listOfUsers(ResultSet resultSet) throws SQLException {
        List<User> listOfUsers = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setLogin(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setRole(RoleEnum.ADMIN);
            listOfUsers.add(user);
        }
        return listOfUsers;
    }

    public List<User> getListOfUsers() throws SQLException {
        String query = "select * from \"UserADMIN\";" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }
    public User getUserADMIN(int id) throws SQLException {
        String query = "select * from \"UserADMIN\" Where id = ?;" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet).get(0);
    }

    public User getByLogin(String login) throws SQLException {
        String query = "select * from \"UserADMIN\" Where login like ?;" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1,"%" + login + "%");
        ResultSet resultSet = statement.executeQuery();
        List<User> userList = listOfUsers(resultSet);
        if (userList.size() == 0) {
            return null;
        }
        else {
            User resUser = userList.get(0);
            return resUser;
        }
    }

    public List<User> loginSearch(String login) throws SQLException {
        if (login.equals(""))
        {
            return getListOfUsers();
        }
        String query = "SELECT * from \"UserADMIN\" where login LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + login + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public List<User> idSearch(String id) throws SQLException {
        if (id.equals(""))
        {
            return getListOfUsers();
        }
        String query = "SELECT * from \"UserADMIN\" where id = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public List<User> nameSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfUsers();
        }
        String query = "SELECT * from \"UserADMIN\" where name LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public List<User> passwordSearch(String password) throws SQLException {
        if (password.equals(""))
        {
            return getListOfUsers();
        }
        String query = "SELECT * from \"UserADMIN\" where password LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + password + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfUsers(resultSet);
    }

    public void updateByLogin(String prevLogin, String newLogin, String newName, String newPassword) throws SQLException {
        String query = "UPDATE \"UserADMIN\" SET login = ?, \"name\" = ?, \"password\" = ? where login = ?;";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1,newLogin);
        statement.setString(2,newName);
        statement.setString(3,newPassword);
        statement.setString(4,prevLogin);
        statement.executeUpdate();
    }

}
