package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.AppointmentDTO;
import lk.ijse.rangabeautysalon.dto.AppointmentTMDTO;
import lk.ijse.rangabeautysalon.dto.DressingappointmentdetailDTO;
import lk.ijse.rangabeautysalon.dto.SalonsappointmentdetailDTO;
import lk.ijse.rangabeautysalon.entity.*;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentService  extends SuperService {
    public boolean save(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
    public boolean saveTransaction(AppointmentTM appointmentTM, Appointment appointment, Salonsappointmentdetail salonsappointmentdetailDTO, Dressingappointmentdetail dressingappointmentdetail) throws SQLException, ClassNotFoundException;
    public boolean update(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public AppointmentDTO search(String id) throws SQLException, ClassNotFoundException;
    public String searchName(String Sname) throws SQLException, ClassNotFoundException;
    public ArrayList<AppointmentDTO> getAllAppointments() throws ClassNotFoundException, SQLException;
    public String generateNextAppointmentId() throws SQLException, ClassNotFoundException;
}
