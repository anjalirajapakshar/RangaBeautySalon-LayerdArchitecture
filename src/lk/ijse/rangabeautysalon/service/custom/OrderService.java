package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.OrderDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.Order;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderService  extends SuperService {
    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    public boolean update(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<OrderDTO> getAllOrders() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadOrderIds() throws SQLException, ClassNotFoundException;
    public String generateNextOrderId() throws SQLException, ClassNotFoundException ;
}
