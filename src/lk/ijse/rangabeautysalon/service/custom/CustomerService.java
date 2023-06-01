package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.CustomerDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerService extends SuperService {
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException;
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomer() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
