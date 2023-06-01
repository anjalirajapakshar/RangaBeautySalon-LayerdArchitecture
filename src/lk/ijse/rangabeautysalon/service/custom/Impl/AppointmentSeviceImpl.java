package lk.ijse.rangabeautysalon.service.custom.Impl;

import javafx.scene.control.Alert;
import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.AppointmentDAO;
import lk.ijse.rangabeautysalon.dao.custom.AppointmentTMDAO;
import lk.ijse.rangabeautysalon.dao.custom.DressingAppointmentDAO;
import lk.ijse.rangabeautysalon.dao.custom.SalonSAppointmentDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.AppointmentDTO;
import lk.ijse.rangabeautysalon.dto.AppointmentTMDTO;
import lk.ijse.rangabeautysalon.dto.DressingappointmentdetailDTO;
import lk.ijse.rangabeautysalon.dto.SalonsappointmentdetailDTO;
import lk.ijse.rangabeautysalon.entity.Appointment;
import lk.ijse.rangabeautysalon.entity.AppointmentTM;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.Salonsappointmentdetail;
import lk.ijse.rangabeautysalon.service.custom.*;
import lk.ijse.rangabeautysalon.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AppointmentSeviceImpl implements AppointmentService{

    private final AppointmentDAO appointmentDAO;
    private final AppointmentTMDAO appointmentTMDAO;
    private final SalonSAppointmentDAO salonSAppointmentDAO;
    private final DressingAppointmentDAO dressingAppointmentDAO;
    private final Convertor convertor;
    private final Connection connection;

    public AppointmentSeviceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        appointmentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.APPOINTMENT );
        appointmentTMDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.APPOINTMENTTM );
        salonSAppointmentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.SALONSAPPOINTMENT );
        dressingAppointmentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.DRESSINGAPPOINTMENT );
        convertor=new Convertor();
    }

    @Override
    public boolean save(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        Boolean save = appointmentDAO.save(convertor.toAppointment(appointmentDTO));
        return save;
    }

    @Override
    public boolean saveTransaction(AppointmentTM appointmentTM, Appointment appointment, Salonsappointmentdetail salonsappointmentdetail, Dressingappointmentdetail dressingappointmentdetail) throws SQLException, ClassNotFoundException {
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isAppTMAdded = appointmentTMDAO.save(appointmentTM);
            System.out.println(isAppTMAdded);
            if(isAppTMAdded) {
                boolean isAdded = appointmentDAO.save(appointment);
                System.out.println(isAdded);
                if(isAdded) {
                    boolean isSSAdded = salonSAppointmentDAO.save(salonsappointmentdetail);
                    System.out.println("isSSAdded : "+isSSAdded);
                    boolean isDressingAdded = dressingAppointmentDAO.save(dressingappointmentdetail);
                    System.out.println("isDre : " + isDressingAdded);
                    if(isSSAdded | isDressingAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Appointment Added!").show();
                        DBConnection.getInstance().getConnection().commit();
                        return true;
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Cannot add Appointment!").show();
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally{
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }
        return false;
    }

    @Override
    public boolean update(AppointmentDTO appointmentDTO) throws SQLException, ClassNotFoundException {
        Boolean update = appointmentDAO.update(convertor.toAppointment(appointmentDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean deleteAppointment = appointmentDAO.deleteAppointment(id);
        return deleteAppointment;
    }

    @Override
    public AppointmentDTO search(String id) throws SQLException, ClassNotFoundException {
        AppointmentDTO appointmentDTO = convertor.fromAppointment(appointmentDAO.search(id));
        return appointmentDTO;
    }

    @Override
    public String searchName(String Sname) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<AppointmentDTO> getAllAppointments() throws ClassNotFoundException, SQLException {
        return (ArrayList<AppointmentDTO>) appointmentDAO.getAllAppointment().stream().map(appointment -> convertor.fromAppointment(appointment)).collect(Collectors.toList());

    }

    @Override
    public String generateNextAppointmentId() throws SQLException, ClassNotFoundException {
        String nextAppointmentId = appointmentDAO.generateNextAppointmentId();
        return nextAppointmentId;
    }
}
