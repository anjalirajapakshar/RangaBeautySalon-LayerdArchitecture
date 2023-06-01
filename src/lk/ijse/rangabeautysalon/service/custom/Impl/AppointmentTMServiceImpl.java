package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.AppointmentTMDAO;
import lk.ijse.rangabeautysalon.dao.custom.RegistrationDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.AppointmentDTO;
import lk.ijse.rangabeautysalon.dto.AppointmentTMDTO;
import lk.ijse.rangabeautysalon.entity.AppointmentTM;
import lk.ijse.rangabeautysalon.service.custom.AppointmentTMService;
import lk.ijse.rangabeautysalon.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AppointmentTMServiceImpl implements AppointmentTMService {
    private final AppointmentTMDAO appointmentTMDAO;
    private final Convertor convertor;
    private final Connection connection;

    public AppointmentTMServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        appointmentTMDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.APPOINTMENTTM );
        convertor=new Convertor();
    }


    @Override
    public boolean save(AppointmentTMDTO appointmentTMDTO) throws SQLException, ClassNotFoundException {
        Boolean save = appointmentTMDAO.save(convertor.toAppointmentTM(appointmentTMDTO));
        return save;
    }

    @Override
    public boolean update(AppointmentTMDTO appointmentTMDTO) throws SQLException, ClassNotFoundException {
        Boolean update = appointmentTMDAO.update(convertor.toAppointmentTM(appointmentTMDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean b = appointmentTMDAO.deleteAppointmentTM(id);
        return b;
    }

    @Override
    public AppointmentTMDTO search(String id) throws SQLException, ClassNotFoundException {
        AppointmentTMDTO appointmentTMDTO = convertor.fromAppointmentTM(appointmentTMDAO.search(id));
        return appointmentTMDTO;
    }

    @Override
    public String searchName(String Sname) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<AppointmentTMDTO> getAllAppointmentTMs() throws ClassNotFoundException, SQLException {
        return (ArrayList<AppointmentTMDTO>) appointmentTMDAO.getAllAppointmentTM().stream().map(appointmentTM -> convertor.fromAppointmentTM(appointmentTM)).collect(Collectors.toList());

    }

    @Override
    public String generateNextAppointmentTMId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
