package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.RegistrationDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.RegistrationDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService{
    private final RegistrationDAO registrationDAO;
    private final Convertor convertor;
    private final Connection connection;

    public RegistrationServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        registrationDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.REGISTRATION );
        convertor=new Convertor();
    }

    @Override
    public Boolean save(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException {
        Boolean save = registrationDAO.save(convertor.toRegistration(registrationDTO));
        return save;
    }

    @Override
    public Boolean update(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public RegistrationDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
