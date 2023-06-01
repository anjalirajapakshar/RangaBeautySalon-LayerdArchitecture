package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.AppointmentDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Appointment;
import lk.ijse.rangabeautysalon.entity.AppointmentTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentImpl implements AppointmentDAO {
    private final Connection connection;

    public AppointmentImpl(Connection connection) {

        this.connection = connection;
    }


    @Override
    public Boolean save(Appointment appointment) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO appointment VALUES(?, ?, ?, ?, ?, ?, ?)",
                appointment.getAppId(),
                appointment.getEmpId(),
                appointment.getcId(),
                appointment.getDescription(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getCost()
        );
    }

    @Override
    public Boolean update(Appointment appointment) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update appointment set empid=?, cid=?, description=?, app_date=?, app_time=?, cost=? where appId=?",
                appointment.getEmpId(),
                appointment.getcId(),
                appointment.getDescription(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getCost(),
                appointment.getAppId()

        );
    }

    @Override
    public boolean deleteAppointment(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From appointment where appId='"+id+"'");
    }

    @Override
    public Appointment search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM appointment WHERE appId = ?", id);

        if(result.next()) {
            return new Appointment(
                    result.getString("appId"),
                    result.getString("empid"),
                    result.getString("cid"),
                    result.getString("description"),
                    result.getString("app_date"),
                    result.getString("app_time"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Appointment> getAllAppointment() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * From appointment");
        ArrayList<Appointment>appointmentList=new ArrayList<>();
        while(result.next()){
            Appointment appointment = new Appointment(result.getString("appId"), result.getString("empid"), result.getString("cid"), result.getString("description"), result.getString("app_date"), result.getString("app_time"),result.getDouble("cost"));
            appointmentList.add(appointment);
        }
        return appointmentList;
    }

    @Override
    public ArrayList<String> loadAppointmentIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextAppointmentId() throws SQLException, ClassNotFoundException {
        String SQL="SELECT appId from appointment order by appId desc limit 1";
        ResultSet result = CrudUtil.execute(SQL);
        if(result.next()){
            return generateNextAppointmentId(result.getString(1));
        }
        return generateNextAppointmentId(result.getString(null));
    }

    private static String generateNextAppointmentId(String CurrentAppId){
        if(CurrentAppId != null){
            String[] split = CurrentAppId.split("App");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "App" + id;
        }
        return "App1";
    }
}
