package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Appointment;
import lk.ijse.rangabeautysalon.entity.Dressing;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DressingDAO extends CrudDAO<Boolean, Dressing> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Dressing search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Dressing> getAllDressing() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadDressingIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadDressingNames() throws SQLException, ClassNotFoundException ;
    public Dressing searchForObject(String name) throws SQLException, ClassNotFoundException ;
    public String searchName(String type) throws SQLException, ClassNotFoundException ;

}
