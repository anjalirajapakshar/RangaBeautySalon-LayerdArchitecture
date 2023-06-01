package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.entity.SalonService;
import lk.ijse.rangabeautysalon.entity.Salonsappointmentdetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalonServiceDAO extends CrudDAO<Boolean, SalonService> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public SalonService search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<SalonService> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
    public String searchName(String Sname) throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadSSNames() throws SQLException, ClassNotFoundException ;
    public SalonService searchForObject(String name) throws SQLException, ClassNotFoundException ;
}
