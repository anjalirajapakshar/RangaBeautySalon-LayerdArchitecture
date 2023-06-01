package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.OrderDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderImpl implements OrderDAO {
    private final Connection connection;

    public OrderImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Order order) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO orderdetail VALUES(?, ?, ?, ?, ?)",
                order.getOrderId(),
                order.getDate(),
                order.getEmpId(),
                order.getCid(),
                order.getCost()
        );
    }

    @Override
    public Boolean update(Order order) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update orderdetail set date=?, empid=?, cid=? where Oid=?",
                order.getDate(),
                order.getEmpId(),
                order.getCid(),
                order.getOrderId()
        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From orderdetail where Oid='"+id+"'");
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM orderdetail WHERE  Oid = ?", id);

        if(result.next()) {
            return new Order(
                    result.getString("Oid"),
                    result.getString("date"),
                    result.getString("empid"),
                    result.getString("cid"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAllOrders() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From orderdetail");
        ArrayList<Order>OrderList=new ArrayList<>();
        while(rst.next()){
            Order order = new Order(
                    rst.getString("Oid"),
                    rst.getString("date"),
                    rst.getString("empid"),
                    rst.getString("cid"),
                    rst.getDouble("cost")
            );
            OrderList.add(order);
        }
        return OrderList;
    }

    @Override
    public ArrayList<String> loadOrderIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Oid FROM orderdetail ORDER BY Oid DESC LIMIT 1";
        ResultSet result = CrudUtil.execute(sql);

        if (result.next()) {
            return generateNextOrderId(result.getString(1));
        }
        return generateNextOrderId(result.getString(null));
    }

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("D");
            //System.out.println(split.toString());
            int id = Integer.parseInt(split[1]);
            //System.out.println(id);
            id += 1;
            return "D" + id;
        }
        return "D1";

    }
}
