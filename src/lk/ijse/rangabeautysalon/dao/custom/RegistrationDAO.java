package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.Registration;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegistrationDAO extends CrudDAO<Boolean, Registration> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Registration search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Registration> getAllRegistrations() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadRegistrationIds() throws SQLException, ClassNotFoundException;
}
