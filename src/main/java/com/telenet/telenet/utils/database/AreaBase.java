package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.enums.roles.RoleEnum;
import com.telenet.telenet.models.user.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AreaBase {
    static DatabaseConsts consts;

    public List<Area> listOfAreas(ResultSet resultSet) throws SQLException {
        List<Area> listOfAreas = new ArrayList<>();
        while (resultSet.next()){
            Area area = new Area();
            area.setId(resultSet.getInt(1));
            area.setName(resultSet.getString(2));
            area.setDescription(resultSet.getString(3));
            listOfAreas.add(area);
        }
        return listOfAreas;
    }

    public List<Area> getListOfAreas() throws SQLException {
        String query = "select * from \"Area\";" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfAreas(resultSet);
    }
    public Area getArea(int id) throws SQLException {
        String query = "select * from \"Area\" Where id = ?;" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfAreas(resultSet).get(0);
    }

    public List<Area> nameSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfAreas();
        }
        String query = "SELECT * from \"Area\" where name LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfAreas(resultSet);
    }

    public List<Area> idSearch(String description) throws SQLException {
        if (description.equals(""))
        {
            return getListOfAreas();
        }
        String query = "SELECT * from \"Area\" where id = ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(description));
        ResultSet resultSet = statement.executeQuery();
        return listOfAreas(resultSet);
    }

    public List<Area> descriptionSearch(String description) throws SQLException {
        if (description.equals(""))
        {
            return getListOfAreas();
        }
        String query = "SELECT * from \"Area\" where description LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + description + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfAreas(resultSet);
    }

    public void updateById(String id, String newName, String newDescription) throws SQLException {
        String query = "UPDATE \"Area\" SET name = ?, \"description\" = ? where id = ?;";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1,newName);
        statement.setString(2,newDescription);
        statement.setInt(3, Integer.parseInt(id));
        statement.executeUpdate();
    }


}
