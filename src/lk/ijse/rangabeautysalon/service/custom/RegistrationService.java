package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dto.RegistrationDTO;
import lk.ijse.rangabeautysalon.entity.Registration;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;

public interface RegistrationService extends SuperService {
    public Boolean save(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException ;
    public Boolean update(RegistrationDTO registrationDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public RegistrationDTO search(String id) throws SQLException, ClassNotFoundException;
}
