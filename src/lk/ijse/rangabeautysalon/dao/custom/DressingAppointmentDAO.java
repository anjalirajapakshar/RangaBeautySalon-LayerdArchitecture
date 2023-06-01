package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Appointment;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DressingAppointmentDAO extends CrudDAO<Boolean, Dressingappointmentdetail> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Dressingappointmentdetail search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Dressingappointmentdetail> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
}
