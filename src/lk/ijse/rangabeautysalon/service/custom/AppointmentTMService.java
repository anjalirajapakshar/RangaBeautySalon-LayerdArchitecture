package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.AppointmentDTO;
import lk.ijse.rangabeautysalon.dto.AppointmentTMDTO;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentTMService extends SuperService {
    public boolean save(AppointmentTMDTO appointmentTMDTO) throws SQLException, ClassNotFoundException;
    public boolean update(AppointmentTMDTO appointmentTMDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public AppointmentTMDTO search(String id) throws SQLException, ClassNotFoundException;
    public String searchName(String Sname) throws SQLException, ClassNotFoundException;
    public ArrayList<AppointmentTMDTO> getAllAppointmentTMs() throws ClassNotFoundException, SQLException;
    public String generateNextAppointmentTMId() throws SQLException, ClassNotFoundException;
}
