package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.entity.AppointmentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentTMDAO extends CrudDAO<Boolean, AppointmentTM> {
    public boolean deleteAppointmentTM(String id) throws ClassNotFoundException, SQLException;
    public AppointmentTM search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<AppointmentTM> getAllAppointmentTM() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadAppointmentTMIds() throws SQLException, ClassNotFoundException;
    public String generateNextAppointmentTMId() throws SQLException, ClassNotFoundException;
}
