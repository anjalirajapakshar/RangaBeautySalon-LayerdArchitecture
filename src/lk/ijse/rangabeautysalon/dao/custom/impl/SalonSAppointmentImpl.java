package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.SalonSAppointmentDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Salonsappointmentdetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalonSAppointmentImpl implements SalonSAppointmentDAO {
    private final Connection connection;

    public SalonSAppointmentImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Salonsappointmentdetail salonsappointmentdetail) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        System.out.println("Salon : "+salonsappointmentdetail.getAppId());
        System.out.println("Salon : "+salonsappointmentdetail.getSSId());
        if(salonsappointmentdetail.getSSId() != null) {
            return CrudUtil.execute("INSERT INTO salonsappointmentdetail VALUES(?, ?, ?)",
                    salonsappointmentdetail.getAppId(),
                    salonsappointmentdetail.getSSId(),
                    salonsappointmentdetail.getDate()
            );
        }
        return false;
    }

    @Override
    public Boolean update(Salonsappointmentdetail salonsappointmentdetail) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update SalonSAppointmentDetail set SS_id=?, date=? where appId=?",
                salonsappointmentdetail.getSSId(),
                salonsappointmentdetail.getDate(),
                salonsappointmentdetail.getAppId()

        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From SalonSAppointmentDetail where appId='"+id+"'");
    }

    @Override
    public Salonsappointmentdetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Salonsappointmentdetail> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
