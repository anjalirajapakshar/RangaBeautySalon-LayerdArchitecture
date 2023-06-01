package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.DressingAppointmentDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DressingAppointmentImpl implements DressingAppointmentDAO{
    private final Connection connection;

    public DressingAppointmentImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Dressingappointmentdetail dressingappointmentdetail) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        System.out.println(dressingappointmentdetail.getAppId());
        System.out.println(dressingappointmentdetail.getDressing_id());
        if (dressingappointmentdetail.getDressing_id() != null ){
            return CrudUtil.execute("INSERT INTO dressingappointmentdetail VALUES(?, ?, ?)",
                    dressingappointmentdetail.getAppId(),
                    dressingappointmentdetail.getDressing_id(),
                    dressingappointmentdetail.getDate()
            );
        }
        return false;
    }

    @Override
    public Boolean update(Dressingappointmentdetail dressingappointmentdetail) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update dressingAppointmentDetail set dressing_id=?, date=? where appId=?",
                dressingappointmentdetail.getDressing_id(),
                dressingappointmentdetail.getDate(),
                dressingappointmentdetail.getAppId()

        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From dressingAppointmentDetail where appId='"+id+"'");
    }

    @Override
    public Dressingappointmentdetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Dressingappointmentdetail> getAll() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }
}
