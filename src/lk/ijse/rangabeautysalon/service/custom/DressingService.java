package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.DressingDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.Dressing;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DressingService  extends SuperService {
    public boolean save(DressingDTO dressingDTO) throws SQLException, ClassNotFoundException;
    public boolean update(DressingDTO dressingDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public DressingDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<DressingDTO> getAllDressing() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadDressingIds() throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadDressingNames() throws SQLException, ClassNotFoundException ;
    public DressingDTO searchForObject(String name) throws SQLException, ClassNotFoundException ;
    public String searchName(String type) throws SQLException, ClassNotFoundException ;
}
