package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.SalonSAppointmentDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.SalonsappointmentdetailDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SalonSAppointmentServiceImpl implements SalonSAppointmentService {
    private final SalonSAppointmentDAO salonSAppointmentDAO;
    private final Convertor convertor;
    private final Connection connection;

    public SalonSAppointmentServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        salonSAppointmentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.SALONSAPPOINTMENT );
        convertor=new Convertor();
    }


    @Override
    public boolean save(SalonsappointmentdetailDTO salonsappointmentdetailDTO) throws SQLException, ClassNotFoundException {
        Boolean save = salonSAppointmentDAO.save(convertor.toSalonsappointmentdetail(salonsappointmentdetailDTO));
        return save;
    }

    @Override
    public boolean update(SalonsappointmentdetailDTO salonsappointmentdetailDTO) throws SQLException, ClassNotFoundException {
        Boolean update = salonSAppointmentDAO.update(convertor.toSalonsappointmentdetail(salonsappointmentdetailDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = salonSAppointmentDAO.delete(id);
        return delete;
    }

    @Override
    public SalonsappointmentdetailDTO search(String id) throws SQLException, ClassNotFoundException {
        SalonsappointmentdetailDTO salonsappointmentdetailDTO = convertor.fromSalonsappointmentdetail(salonSAppointmentDAO.search(id));
        return salonsappointmentdetailDTO;
    }

    @Override
    public ArrayList<SalonsappointmentdetailDTO> getAll() throws ClassNotFoundException, SQLException {
        return (ArrayList<SalonsappointmentdetailDTO>) salonSAppointmentDAO.getAll().stream().map(salonsappointmentdetail -> convertor.fromSalonsappointmentdetail(salonsappointmentdetail)).collect(Collectors.toList());

    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = salonSAppointmentDAO.loadIds();
        return strings;
    }
}
