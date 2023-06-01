package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Boolean, Order> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Order search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Order> getAllOrders() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadOrderIds() throws SQLException, ClassNotFoundException;
    public String generateNextOrderId() throws SQLException, ClassNotFoundException ;
}
