package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Boolean, Employee> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Employee search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Employee> getAllEmployee() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException;
}
