package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.ExtraExpenseDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.entity.ExtraExpense;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExtraExpenseService extends SuperService {
    public boolean save(ExtraExpenseDTO extraExpenseDTO) throws SQLException, ClassNotFoundException;
    public boolean update(ExtraExpenseDTO extraExpenseDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public ExtraExpenseDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<ExtraExpenseDTO> getAllExtraExpenses() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadExtraExpenseIds() throws SQLException, ClassNotFoundException;
    public String generateNextExExpenseId() throws SQLException, ClassNotFoundException ;
}
