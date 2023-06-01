package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.OrderProductDetailDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.OrderProductDetail;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderProductService extends SuperService {
    public boolean save(OrderProductDetailDTO orderProductDetailDTO) throws SQLException, ClassNotFoundException;
    public boolean update(OrderProductDetailDTO orderProductDetailDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public OrderProductDetailDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<OrderProductDetailDTO> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
}
