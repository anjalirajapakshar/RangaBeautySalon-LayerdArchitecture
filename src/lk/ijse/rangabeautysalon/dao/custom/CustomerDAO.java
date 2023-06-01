package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Boolean,Customer>{
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException;
    public Customer search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Customer> getAllCustomer() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException;

}
