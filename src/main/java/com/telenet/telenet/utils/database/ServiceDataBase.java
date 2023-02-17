package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServiceDataBase {
    static DataBaseConsts consts;
    @Autowired
    TemplateDataBase templateDataBase;
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;

    public List<Service> listOfServices(ResultSet resultSet) throws SQLException {
        List<Service> listOfServices = new ArrayList<>();
        while (resultSet.next()){
            Service service = new Service();
            service.setId(resultSet.getInt(1));
            service.setName(resultSet.getString(2));
            service.setDescription(resultSet.getString(3));
            service.setPrice(resultSet.getDouble(4));
            service.setTemplate(templateDataBase.getTemplate(resultSet.getInt(5)));
            service.setUser(userDEFAULT_userDataBase.getUserDEFAULT_USER(resultSet.getInt(6)));
            service.setStatus(StatusEnum.valueOf(resultSet.getString(7)));
            listOfServices.add(service);
        }
        return listOfServices;
    }
    public List<Service> getListOfServices() throws SQLException {
        String query = "select * from \"Service\";" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }
    public Service getService(int id) throws SQLException {
        String query = "select * from \"Service\" Where id = ?;" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet).get(0);
    }

    public List<Service> nameSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where name LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public List<Service> descriptionSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where description LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public List<Service> priceSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where price = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setDouble(1, Double.parseDouble(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public List<Service> templateSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where \"templateId\" = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public List<Service> userSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where \"userId\" = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public List<Service> idSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where \"id\" = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(name));
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public List<Service> statusSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfServices();
        }
        String query = "SELECT * from \"Service\" where status LIKE ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfServices(resultSet);
    }

    public void updateById(String id, String newName, String newDescription, String newPrice, String newTemplateId,
                           String newUserId, String newStatus) throws SQLException {
        String query = "UPDATE \"Service\" SET name = ?, \"description\" = ?, price = ?, \"templateId\" = ?, \"userId\" = ?, \"status\" = ? where id = ?;";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1,newName);
        statement.setString(2,newDescription);
        statement.setDouble(3, Double.parseDouble(newPrice));
        statement.setInt(4, Integer.parseInt(newTemplateId));
        statement.setInt(5, Integer.parseInt(id));
        statement.setString(6,newStatus);
        statement.setInt(7,Integer.parseInt(id));
        statement.executeUpdate();
    }
    public List<Service> getActiveServiceListByUserImplLogin(String login) throws SQLException {
        return getListOfServices().stream().filter(service -> login.equals(service.getUser().getLogin()) && service.getStatus().equals(StatusEnum.ACTIVE)).toList();
    }
}
