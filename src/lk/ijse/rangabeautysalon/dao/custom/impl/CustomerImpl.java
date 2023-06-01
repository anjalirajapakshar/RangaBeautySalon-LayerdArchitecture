package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.CustomerDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.SuperEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerImpl implements CustomerDAO {
    private final Connection connection;

    public CustomerImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Boolean save(Customer customer) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?, ?, ?, ?,?)",
                customer.getCustID(),
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getNic()
        );
    }

    @Override
    public Boolean update(Customer customer) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update Customer set name=?, address=?, tel=?, nic=? where cid=?",
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getNic(),
                customer.getCustID()
        );
    }


    @Override
    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From Customer where cid='"+id+"'");
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE cid = ?", id);

        if(result.next()) {
            return new Customer(
                    result.getString("cid"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getInt("tel"),
                    result.getString("nic")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From Customer");
        ArrayList<Customer>customerList=new ArrayList<>();
        while(rst.next()){
            Customer customer = new Customer(rst.getString("cid"), rst.getString("name"), rst.getString("address"), rst.getInt("tel"),rst.getString("nic"));
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public ArrayList<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT cid FROM Customer";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> idList = new ArrayList<>();

        while (result.next()) {
            idList.add(result.getString(1));
        }
        return idList;
    }
}
