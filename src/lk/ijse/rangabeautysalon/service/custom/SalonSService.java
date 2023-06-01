package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.SalonServiceDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.SalonService;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalonSService  extends SuperService {
    public boolean save(SalonServiceDTO salonServiceDTO) throws SQLException, ClassNotFoundException;
    public boolean update(SalonServiceDTO salonServiceDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public SalonServiceDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<SalonServiceDTO> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
    public String searchName(String Sname) throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadSSNames() throws SQLException, ClassNotFoundException ;
    public SalonServiceDTO searchForObject(String name) throws SQLException, ClassNotFoundException ;
}
