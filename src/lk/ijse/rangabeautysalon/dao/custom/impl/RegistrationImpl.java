package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.RegistrationDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Registration;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationImpl implements RegistrationDAO {
    private final Connection connection;

    public RegistrationImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Registration registration) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into loginRegistration(userName,password,Role) values(?,?,?)",
                registration.getUserName(),
                registration.getPassword(),
                registration.getRole()
        );
    }

    @Override
    public Boolean update(Registration registration) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Registration search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Registration> getAllRegistrations() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> loadRegistrationIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
