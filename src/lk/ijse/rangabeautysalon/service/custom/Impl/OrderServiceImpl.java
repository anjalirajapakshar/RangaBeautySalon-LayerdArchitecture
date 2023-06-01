package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.OrderDAO;
import lk.ijse.rangabeautysalon.dao.custom.OrderProductDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.OrderDTO;
import lk.ijse.rangabeautysalon.entity.Order;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService{
    private final OrderDAO orderDAO;
    private final Convertor convertor;
    private final Connection connection;

    public OrderServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        orderDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.ORDER );
        convertor=new Convertor();
    }

    @Override
    public boolean save(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Boolean save = orderDAO.save(convertor.toOrder(orderDTO));
        return save;
    }

    @Override
    public boolean update(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Boolean update = orderDAO.update(convertor.toOrder(orderDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = orderDAO.delete(id);
        return delete;
    }

    @Override
    public OrderDTO search(String id) throws SQLException, ClassNotFoundException {
        OrderDTO orderDTO = convertor.fromOrder(orderDAO.search(id));
        return orderDTO;
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws ClassNotFoundException, SQLException {
        return (ArrayList<OrderDTO>) orderDAO.getAllOrders().stream().map(orders -> convertor.fromOrder(orders)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadOrderIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = orderDAO.loadOrderIds();
        return strings;
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        String s = orderDAO.generateNextOrderId();
        return s;
    }
}
