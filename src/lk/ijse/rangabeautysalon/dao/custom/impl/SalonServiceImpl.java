package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.SalonServiceDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.SalonService;
import lk.ijse.rangabeautysalon.entity.Salonsappointmentdetail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalonServiceImpl implements SalonServiceDAO {
    private final Connection connection;

    public SalonServiceImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From salonservice where SS_id='"+id+"'");
    }

    @Override
    public SalonService search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM salonservice WHERE SS_id = ?", id);

        if(result.next()) {
            return new SalonService(
                    result.getString("SS_id"),
                    result.getString("Sname"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    @Override
    public ArrayList<SalonService> getAll() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From salonservice");
        ArrayList<SalonService>SalonServiceList=new ArrayList<>();
        while(rst.next()){
            SalonService salonService = new SalonService(rst.getString("SS_id"), rst.getString("Sname"), rst.getDouble("cost"));
            SalonServiceList.add(salonService);
        }
        return SalonServiceList;
    }

    @Override
    public ArrayList<String> loadIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String searchName(String Sname) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("select ss_id from salonservice where Sname= ? ", Sname);

        if(result.next()) {
            return result.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<String> loadSSNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT Sname FROM salonservice";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> NameList = new ArrayList<>();

        while (result.next()) {
            NameList.add(result.getString("Sname"));
        }
        return NameList;
    }

    @Override
    public SalonService searchForObject(String name) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM salonservice WHERE Sname = ?", name);

        if(result.next()) {
            return new SalonService(
                    result.getString("SS_id"),
                    result.getString("Sname"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    @Override
    public Boolean save(SalonService salonService) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO salonservice VALUES(?, ?, ?)",
                salonService.getSSId(),
                salonService.getServiceName(),
                salonService.getCost()
        );
    }

    @Override
    public Boolean update(SalonService salonService) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update salonservice set Sname=?, cost=? where SS_id=?",
                salonService.getServiceName(),
                salonService.getCost(),
                salonService.getSSId()
        );
    }
}
