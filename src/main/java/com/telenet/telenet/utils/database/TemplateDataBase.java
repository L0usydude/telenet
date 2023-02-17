package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TemplateDataBase {
    static DataBaseConsts consts;
    @Autowired
    AreaDataBase areaDataBase;

    public List<Template> listOfTemplates(ResultSet resultSet) throws SQLException {
        List<Template> listOfTemplates = new ArrayList<>();
        while (resultSet.next()){
            Template template = new Template();
            template.setId(resultSet.getInt(1));
            template.setName(resultSet.getString(2));
            template.setDescription(resultSet.getString(3));
            template.setPrice(resultSet.getDouble(4));
            template.setArea(areaDataBase.getArea(resultSet.getInt(5)));
            listOfTemplates.add(template);
        }
        return listOfTemplates;
    }

    public List<Template> getListOfTemplates() throws SQLException {
        String query = "select * from \"Template\";";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet);
    }

    public Template getTemplate(int id) throws SQLException {
        String query = "select * from \"Template\" Where id = ?;" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet).get(0);
    }

    public List<Template> nameSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfTemplates();
        }
        String query = "SELECT * from \"Template\" where name LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet);
    }

    public List<Template> descriptionSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfTemplates();
        }
        String query = "SELECT * from \"Template\" where description LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet);
    }

    public List<Template> priceSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfTemplates();
        }
        String query = "SELECT * from \"Template\" where price = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setDouble(1, Double.parseDouble(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet);
    }

    public List<Template> idSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfTemplates();
        }
        String query = "SELECT * from \"Template\" where id = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setDouble(1, Double.parseDouble(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet);
    }

    public List<Template> areaSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfTemplates();
        }
        String query = "SELECT * from \"Template\" where \"areaId\" = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfTemplates(resultSet);
    }

    public void updateById(String id, String newName, String newDescription, String newPrice, String newAreaId) throws SQLException {
        String query = "UPDATE \"Template\" SET name = ?, \"description\" = ?, price = ?, \"areaId\" = ? where id = ?;";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1,newName);
        statement.setString(2,newDescription);
        statement.setDouble(3, Double.parseDouble(newPrice));
        statement.setInt(4, Integer.parseInt(newAreaId));
        statement.setInt(5, Integer.parseInt(id));
        statement.executeUpdate();
    }
}
