package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.CustomerDAO;
import lk.ijse.rangabeautysalon.dao.custom.DressingAppointmentDAO;
import lk.ijse.rangabeautysalon.dao.custom.DressingDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.DressingappointmentdetailDTO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lk.ijse.rangabeautysalon.service.custom.*;

public class DressingAppointmentServiceImpl implements DressingAppointmentService{
    private final DressingAppointmentDAO dressingAppointmentDAO;
    private final Convertor convertor;
    private final Connection connection;

    public DressingAppointmentServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        dressingAppointmentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.DRESSINGAPPOINTMENT );
        convertor=new Convertor();
    }


    @Override
    public boolean save(DressingappointmentdetailDTO dressingappointmentdetailDTO) throws SQLException, ClassNotFoundException {
        Boolean save = dressingAppointmentDAO.save(convertor.toDressingappointmentdetail(dressingappointmentdetailDTO));
        return save;
    }

    @Override
    public boolean update(DressingappointmentdetailDTO dressingappointmentdetailDTO) throws SQLException, ClassNotFoundException {
        Boolean update = dressingAppointmentDAO.update(convertor.toDressingappointmentdetail(dressingappointmentdetailDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = dressingAppointmentDAO.delete(id);
        return delete;
    }

    @Override
    public DressingappointmentdetailDTO search(String id) throws SQLException, ClassNotFoundException {
        DressingappointmentdetailDTO dressingappointmentdetailDTO = convertor.fromDressingappointmentdetail(dressingAppointmentDAO.search(id));
        return dressingappointmentdetailDTO;
    }

    @Override
    public ArrayList<DressingappointmentdetailDTO> getAll() throws ClassNotFoundException, SQLException {
        return (ArrayList<DressingappointmentdetailDTO>) dressingAppointmentDAO.getAll().stream().map(dressingappointmentdetail -> convertor.fromDressingappointmentdetail(dressingappointmentdetail)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = dressingAppointmentDAO.loadIds();
        return ids;
    }
}
