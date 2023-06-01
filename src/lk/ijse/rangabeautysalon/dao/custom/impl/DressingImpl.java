package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.DressingDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Dressing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DressingImpl implements DressingDAO{
    private final Connection connection;

    public DressingImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Dressing dressing) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO dressing VALUES(?, ?, ?)",
                dressing.getDressingId(),
                dressing.getType(),
                dressing.getCost()
        );
    }

    @Override
    public Boolean update(Dressing dressing) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update dressing set type=?, cost=? where dressing_id=?",
                dressing.getType(),
                dressing.getCost(),
                dressing.getDressingId()

        );
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return  CrudUtil.execute("Delete From dressing where dressing_id='"+id+"'");
    }

    @Override
    public Dressing search(String type) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("select dressing_id from dressing where type= ? ", type);

        if(result.next()) {
            return new Dressing(
                    result.getString("dressing_id"),
                    result.getString("type"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Dressing> getAllDressing() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * From dressing");
        ArrayList<Dressing>dressingList=new ArrayList<>();
        while(rst.next()){
            Dressing dressing = new Dressing(rst.getString("dressing_id"), rst.getString("type"), rst.getDouble("cost"));
            dressingList.add(dressing);
        }
        return dressingList;
    }

    @Override
    public ArrayList<String> loadDressingIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> loadDressingNames() throws SQLException, ClassNotFoundException {
        String sql = "SELECT type FROM dressing";
        ResultSet result = CrudUtil.execute(sql);

        ArrayList<String> NameList = new ArrayList<>();

        while (result.next()) {
            NameList.add(result.getString("type"));
        }
        return NameList;
    }

    @Override
    public Dressing searchForObject(String name) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM dressing WHERE type = ?", name);

        if(result.next()) {
            return new Dressing(
                    result.getString("dressing_id"),
                    result.getString("type"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    public String searchName(String type) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("select dressing_id from dressing where type= ? ", type);

        if(result.next()) {
            return result.getString(1);
        }
        return null;
    }

}
