package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.DressingappointmentdetailDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DressingAppointmentService  extends SuperService {
    public boolean save(DressingappointmentdetailDTO dressingappointmentdetailDTO) throws SQLException, ClassNotFoundException;
    public boolean update(DressingappointmentdetailDTO dressingappointmentdetailDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public DressingappointmentdetailDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<DressingappointmentdetailDTO> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
}
