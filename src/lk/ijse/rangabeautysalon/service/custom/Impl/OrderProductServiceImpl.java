package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.ExtraExpenseDAO;
import lk.ijse.rangabeautysalon.dao.custom.OrderProductDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.OrderProductDetailDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderProductServiceImpl implements OrderProductService{
    private final OrderProductDAO orderProductDAO;
    private final Convertor convertor;
    private final Connection connection;

    public OrderProductServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        orderProductDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.ORDERPRODUCT );
        convertor=new Convertor();
    }

    @Override
    public boolean save(OrderProductDetailDTO orderProductDetailDTO) throws SQLException, ClassNotFoundException {
        Boolean save = orderProductDAO.save(convertor.toOrderProductDetail(orderProductDetailDTO));
        return save;
    }

    @Override
    public boolean update(OrderProductDetailDTO orderProductDetailDTO) throws SQLException, ClassNotFoundException {
        Boolean update = orderProductDAO.update(convertor.toOrderProductDetail(orderProductDetailDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = orderProductDAO.delete(id);
        return delete;
    }

    @Override
    public OrderProductDetailDTO search(String id) throws SQLException, ClassNotFoundException {
        OrderProductDetailDTO orderProductDetailDTO = convertor.fromOrderProductDetail(orderProductDAO.search(id));
        return orderProductDetailDTO;
    }

    @Override
    public ArrayList<OrderProductDetailDTO> getAll() throws ClassNotFoundException, SQLException {
        return (ArrayList<OrderProductDetailDTO>) orderProductDAO.getAll().stream().map(orderProductDetail -> convertor.fromOrderProductDetail(orderProductDetail)).collect(Collectors.toList());

    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = orderProductDAO.loadIds();
        return strings;
    }
}
