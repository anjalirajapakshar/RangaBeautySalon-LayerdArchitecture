package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.DressingDAO;
import lk.ijse.rangabeautysalon.dao.custom.EmployeeDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.EmployeeDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeDAO employeeDAO;
    private final Convertor convertor;
    private final Connection connection;

    public EmployeeServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        employeeDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.EMPLOYEE );
        convertor=new Convertor();
    }

    @Override
    public boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        Boolean save = employeeDAO.save(convertor.toEmployee(employeeDTO));
        return save;
    }

    @Override
    public boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        Boolean update = employeeDAO.update(convertor.toEmployee(employeeDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = employeeDAO.delete(id);
        return delete;
    }

    @Override
    public EmployeeDTO search(String id) throws SQLException, ClassNotFoundException {
        EmployeeDTO employeeDTO = convertor.fromEmployee(employeeDAO.search(id));
        return employeeDTO;
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws ClassNotFoundException, SQLException {
        return (ArrayList<EmployeeDTO>) employeeDAO.getAllEmployee().stream().map(employee -> convertor.fromEmployee(employee)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadEmployeeIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> employeeIds = employeeDAO.loadEmployeeIds();
        return employeeIds;
    }
}
