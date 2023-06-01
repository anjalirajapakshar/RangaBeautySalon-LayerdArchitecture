package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.ExtraExpenseDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.ExtraExpense;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExtraExpenseImpl implements ExtraExpenseDAO {
    private final Connection connection;

    public ExtraExpenseImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(ExtraExpense extraExpense) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO extraexpence VALUES(?, ?, ?)",
                extraExpense.getExId(),
                extraExpense.getDesc(),
                extraExpense.getCost()
        );
    }

    @Override
    public Boolean update(ExtraExpense extraExpense) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public ExtraExpense search(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM extraexpence WHERE ExId = ?", id);

        if(result.next()) {
            return new ExtraExpense(
                    result.getString("ExId"),
                    result.getString("description"),
                    result.getDouble("cost")
            );
        }
        return null;
    }

    @Override
    public ArrayList<ExtraExpense> getAllExtraExpenses() throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ArrayList<String> loadExtraExpenseIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNextExExpenseId() throws SQLException, ClassNotFoundException {
        String SQL="SELECT ExId from extraexpence order by ExId desc limit 1";
        ResultSet result = CrudUtil.execute(SQL);
        if(result.next()){
            return generateNextExExpenseId(result.getString(1));
        }
        return generateNextExExpenseId(result.getString(null));
    }

    private static String generateNextExExpenseId(String CurrentgenerateNextExExpenseId){
        if(CurrentgenerateNextExExpenseId != null){
            String[] split = CurrentgenerateNextExExpenseId.split("Ex");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "Ex" + id;
        }
        return "Ex1";
    }

}
