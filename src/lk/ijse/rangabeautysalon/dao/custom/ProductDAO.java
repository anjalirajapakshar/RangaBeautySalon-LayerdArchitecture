package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudDAO<Boolean, Product> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Product search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Product> getAllProduct() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadProductIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadProductNames() throws SQLException, ClassNotFoundException ;
    public Product searchName(String name) throws SQLException, ClassNotFoundException ;
}
