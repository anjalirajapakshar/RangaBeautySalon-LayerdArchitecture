package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.EmployeeDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeImpl implements EmployeeDAO {
    private final Connection connection;

    public EmployeeImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Employee employee) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?)",
                employee.getEmpId(),
                employee.getName(),
                employee.getAddress(),
                employee.getTel(),
                employee.getNic()
        );
    }

    @Override
    public Boolean update(Employee employee) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update employee set name=?, address=?, tel=?, nic=? where empid=?",
                employee.getName(),
                employee.getAddress(),
                employee.getTel(),
                employee.getNic(),
                employee.getEmpId()
        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From employee where empid='"+id+"'");
    }

    @Override
    public Employee search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM employee WHERE empid = ?", id);

        if(result.next()) {
            return new Employee(
                    result.getString("empid"),
                    result.getString("name"),
                    result.getString("address"),
                    result.getInt("tel"),
                    result.getString("nic")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From employee");
        ArrayList<Employee>employeeList=new ArrayList<>();
        while(rst.next()){
            Employee employee = new Employee(rst.getString("empid"), rst.getString("name"), rst.getString("address"), rst.getInt("tel"),rst.getString("nic"));
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
