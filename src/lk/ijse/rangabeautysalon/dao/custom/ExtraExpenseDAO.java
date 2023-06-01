package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.dao.SuperDAO;
import lk.ijse.rangabeautysalon.entity.Dressingappointmentdetail;
import lk.ijse.rangabeautysalon.entity.ExtraExpense;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExtraExpenseDAO extends CrudDAO<Boolean, ExtraExpense> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public ExtraExpense search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<ExtraExpense> getAllExtraExpenses() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadExtraExpenseIds() throws SQLException, ClassNotFoundException;
    public String generateNextExExpenseId() throws SQLException, ClassNotFoundException ;
}
