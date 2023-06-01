package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.AppointmentTMDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.AppointmentTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentTMImpl implements AppointmentTMDAO {
    private final Connection connection;

    public AppointmentTMImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(AppointmentTM appointmentTM) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO AppointmentTM VALUES(?, ?, ?, ?, ?, ?, ?)",
                appointmentTM.getAppId(),
                appointmentTM.getCID(),
                appointmentTM.getDescription(),
                appointmentTM.getSalonService(),
                appointmentTM.getDressingId(),
                appointmentTM.getDate(),
                appointmentTM.getTime()
        );
    }

    @Override
    public Boolean update(AppointmentTM appointmentTM) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update appointmentTM set cid=?, Description=?, SalonService=?, DressingId=?, date=?, time=? where appId=?",
                appointmentTM.getCID(),
                appointmentTM.getDescription(),
                appointmentTM.getSalonService(),
                appointmentTM.getDressingId(),
                appointmentTM.getDate(),
                appointmentTM.getTime(),
                appointmentTM.getAppId()
        );
    }


    @Override
    public boolean deleteAppointmentTM(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From appointmentTM where appId='"+id+"'");
    }

    @Override
    public AppointmentTM search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM appointmentTM WHERE appId = ?", id);

        if(result.next()) {
            return new AppointmentTM(
                    result.getString("appId"),
                    result.getString("cid"),
                    result.getString("description"),
                    result.getString("SalonService"),
                    result.getString("DressingId"),
                    result.getString("date"),
                    result.getString("time")
            );
        }
        return null;
    }

    @Override
    public ArrayList<AppointmentTM> getAllAppointmentTM() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * From AppointmentTM");
        ArrayList<AppointmentTM>appointmentTMsList=new ArrayList<>();
        while(result.next()){
            AppointmentTM appointmentTM = new AppointmentTM(result.getString("AppId"), result.getString("CID"),result.getString("Description"), result.getString("SalonService"), result.getString("DressingId"), result.getString("date"), result.getString("time"));
            appointmentTMsList.add(appointmentTM);
        }
        return appointmentTMsList;
    }

    @Override
    public ArrayList<String> loadAppointmentTMIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextAppointmentTMId() throws SQLException, ClassNotFoundException {
        return null;
    }


}
