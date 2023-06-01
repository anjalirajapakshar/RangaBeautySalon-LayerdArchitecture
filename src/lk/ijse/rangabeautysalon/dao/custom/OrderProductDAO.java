package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.OrderProductDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderProductDAO  extends CrudDAO<Boolean, OrderProductDetail> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public OrderProductDetail search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<OrderProductDetail> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;

}
