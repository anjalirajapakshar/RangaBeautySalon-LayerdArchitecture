package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.EmployeeDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.Employee;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeService  extends SuperService {
    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<EmployeeDTO> getAllEmployee() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException;
}
