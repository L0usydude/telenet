package com.telenet.telenet.utils.database;

import com.telenet.telenet.models.enums.action.ActionEnum;
import com.telenet.telenet.models.enums.status.StatusEnum;
import com.telenet.telenet.models.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDataBase {
    static DataBaseConsts consts;
    @Autowired
    ServiceDataBase serviceDataBase;
    @Autowired
    UserDEFAULT_USERDataBase userDEFAULT_userDataBase;

    public List<Order> listOfOrders(ResultSet resultSet) throws SQLException {
        List<Order> listOfOrders = new ArrayList<>();
        while (resultSet.next()){
            Order order = new Order();
            order.setId(resultSet.getInt(1));
            order.setUser(userDEFAULT_userDataBase.getUserDEFAULT_USER(resultSet.getInt(2)));
            order.setService(serviceDataBase.getService(resultSet.getInt(3)));
            order.setStatus(StatusEnum.valueOf(resultSet.getString(4)));
            order.setAction(ActionEnum.valueOf(resultSet.getString(5)));
            listOfOrders.add(order);
        }
        return listOfOrders;
    }

    public List<Order> getListOfOrders() throws SQLException {
        String query = "select * from \"Order\";" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public Order getOrder(int id) throws SQLException {
        String query = "select * from \"Order\" Where id = ?;" ;
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
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
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
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
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
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
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
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
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
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
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        return listOfOrders(resultSet);
    }

    public void updateById(String id, String newUserId, String newServiceId, String newStatus, String newAction) throws SQLException {
        String query = "UPDATE \"Order\" SET \"userId\" = ?, \"serviceId\" = ?, \"status\" = ?, \"action\" = ? where id = ?;";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1,Integer.parseInt(newUserId));
        statement.setInt(2,Integer.parseInt(newServiceId));
        statement.setString(3,newStatus);
        statement.setString(4, newAction);
        statement.setInt(5, Integer.parseInt(id));
        statement.executeUpdate();
    }

    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO \"Order\" values(?,?,?,?,?)";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1,order.getId());
        statement.setInt(2, order.getUser().getId());
        statement.setInt(3, order.getService().getId());
        statement.setString(4,order.getStatus().toString());
        statement.setString(5,order.getAction().toString());
        statement.executeUpdate();
    }

    public void delOrder(int id) throws SQLException {
        String query = "DELETE from \"Order\" WHERE id = ?";
        PreparedStatement statement = DataBaseConsts.connection.prepareStatement(query);
        statement.setInt(1,id);
        statement.executeUpdate();

    }
    public List<Order> getOrderListByUserImplLogin(String login) throws SQLException {
        return getListOfOrders().stream().filter(order -> login.equals(order.getUser().getLogin())).toList();
    }

}
