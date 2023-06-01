package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.Salonsappointmentdetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalonSAppointmentDAO extends CrudDAO<Boolean, Salonsappointmentdetail> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Salonsappointmentdetail search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Salonsappointmentdetail> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
}
