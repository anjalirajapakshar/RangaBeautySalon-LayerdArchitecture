package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.SalonsappointmentdetailDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.Salonsappointmentdetail;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalonSAppointmentService  extends SuperService {
    public boolean save(SalonsappointmentdetailDTO salonsappointmentdetailDTO) throws SQLException, ClassNotFoundException;
    public boolean update(SalonsappointmentdetailDTO salonsappointmentdetailDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public SalonsappointmentdetailDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<SalonsappointmentdetailDTO> getAll() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException;
}
