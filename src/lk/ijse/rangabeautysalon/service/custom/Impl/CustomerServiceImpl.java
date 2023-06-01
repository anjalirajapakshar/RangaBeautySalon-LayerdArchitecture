package lk.ijse.rangabeautysalon.service.custom.Impl;
import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.AppointmentDAO;
import lk.ijse.rangabeautysalon.dao.custom.CustomerDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.AppointmentDTO;
import lk.ijse.rangabeautysalon.dto.CustomerDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.service.custom.*;
import lk.ijse.rangabeautysalon.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerDAO customerDAO;
    private final Convertor convertor;
    private final Connection connection;

    public CustomerServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        customerDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.CUSTOMER );
        convertor=new Convertor();
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Boolean save = customerDAO.save(convertor.toCustomer(customerDTO));
        return save;
    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Boolean update = customerDAO.update(convertor.toCustomer(customerDTO));
        return update;
    }

    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        boolean deleteCustomer = customerDAO.deleteCustomer(id);
        return deleteCustomer;
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = convertor.fromCustomer(customerDAO.search(id));
        return customerDTO;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws ClassNotFoundException, SQLException {
        return (ArrayList<CustomerDTO>) customerDAO.getAllCustomer().stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerDAO.loadCustomerIds();
        return customerIds;
    }
}
