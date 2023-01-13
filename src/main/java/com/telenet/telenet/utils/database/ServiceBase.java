package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.area.Area;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceBase {
    static DatabaseConsts consts;
    @Autowired
    TemplateBase templateBase;
    @Autowired
    UserDEFAULT_USERBase userDEFAULT_userBase;

    public List<Service> listOfServices(ResultSet resultSet) throws SQLException {
        List<Service> listOfServices = new ArrayList<>();
        while (resultSet.next()){
            Service service = new Service();
            service.setId(resultSet.getInt(1));
            service.setName(resultSet.getString(2));
            service.setDescription(resultSet.getString(3));
            service.setPrice(resultSet.getDouble(4));
            service.setTemplate(templateBase.getTemplate(resultSet.getInt(5)));
            service.setUser(userDEFAULT_userBase.getUserDEFAULT_USER(resultSet.getInt(6)));
            service.setStatus(StatusEnum.valueOf(resultSet.getString(7)));
            listOfServices.add(service);
        }
        return listOfServices;
    }
    public List<Service> getListOfServices() throws SQLException {
        String query = "select * from \"Service\";" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }
    public Service getService(int id) throws SQLException {
        String query = "select * from \"Service\" Where id = ?;" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet).get(0);
    }

    public List<Service> nameSearch(String name) throws SQLException {
        String query = "SELECT * from \"Service\" where name LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public void updateById(String id, String newName, String newDescription, String newPrice, String newTemplateId,
                           String newUserId, String newStatus) throws SQLException {
        String query = "UPDATE \"Service\" SET name = ?, \"description\" = ?, price = ?, \"templateId\" = ?, \"userId\" = ?, \"status\" = ? where id = ?;";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1,newName);
        statement.setString(2,newDescription);
        statement.setDouble(3, Double.parseDouble(newPrice));
        statement.setInt(4, Integer.parseInt(newTemplateId));
        statement.setInt(5, Integer.parseInt(id));
        statement.setString(6,newStatus);
        statement.setInt(7,Integer.parseInt(id));
        statement.executeUpdate();
    }
}
