package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.entity.Appointment;
import lk.ijse.rangabeautysalon.entity.AppointmentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends CrudDAO<Boolean, Appointment>{
    public boolean deleteAppointment(String id) throws ClassNotFoundException, SQLException;
    public Appointment search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Appointment> getAllAppointment() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadAppointmentIds() throws SQLException, ClassNotFoundException;
    public String generateNextAppointmentId() throws SQLException, ClassNotFoundException;
}
