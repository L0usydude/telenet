package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.order.Order;
import com.telenet.telenet.models.service.Service;
import com.telenet.telenet.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderBase {
    static DatabaseConsts consts;
    @Autowired
    ServiceBase serviceBase;
    @Autowired
    UserDEFAULT_USERBase userDEFAULT_userBase;

    public List<Order> listOfOrders(ResultSet resultSet) throws SQLException {
        List<Order> listOfOrders = new ArrayList<>();
        while (resultSet.next()){
            Order order = new Order();
            order.setId(resultSet.getInt(1));
            order.setUser(userDEFAULT_userBase.getUserDEFAULT_USER(resultSet.getInt(2)));
            order.setService(serviceBase.getService(resultSet.getInt(3)));
            order.setStatus(StatusEnum.valueOf(resultSet.getString(4)));
            order.setAction(ActionEnum.valueOf(resultSet.getString(5)));
            listOfOrders.add(order);
        }
        return listOfOrders;
    }

    public List<Order> getListOfOrders() throws SQLException {
        String query = "select * from \"Order\";" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public Order getOrder(int id) throws SQLException {
        String query = "select * from \"Order\" Where id = ?;" ;
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet).get(0);
    }

    public List<Order> userSearch(String userId) throws SQLException {
        if (userId.equals(""))
        {
            return getListOfOrders();
        }
        String query = "SELECT * from \"Order\" where \"userId\" = ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(userId));
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public List<Order> idSearch(String userId) throws SQLException {
        if (userId.equals(""))
        {
            return getListOfOrders();
        }
        String query = "SELECT * from \"Order\" where \"id\" = ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(userId));
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }


    public List<Order> serviceSearch(String userId) throws SQLException {
        if (userId.equals(""))
        {
            return getListOfOrders();
        }
        String query = "SELECT * from \"Order\" where \"serviceId\" = ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1, Integer.parseInt(userId));
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public List<Order> statusSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfOrders();
        }
        String query = "SELECT * from \"Order\" where status LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public List<Order> actionSearch(String name) throws SQLException {
        if (name.equals(""))
        {
            return getListOfOrders();
        }
        String query = "SELECT * from \"Order\" where action LIKE ?";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public void updateById(String id, String newUserId, String newServiceId, String newStatus, String newAction) throws SQLException {
        String query = "UPDATE \"Order\" SET \"userId\" = ?, \"serviceId\" = ?, \"status\" = ?, \"action\" = ? where id = ?;";
        PreparedStatement statement = DatabaseConsts.connection.prepareStatement(query);
        statement.setInt(1,Integer.parseInt(newUserId));
        statement.setInt(2,Integer.parseInt(newServiceId));
        statement.setString(3,newStatus);
        statement.setString(4, newAction);
        statement.setInt(5, Integer.parseInt(id));
        statement.executeUpdate();
    }

}
