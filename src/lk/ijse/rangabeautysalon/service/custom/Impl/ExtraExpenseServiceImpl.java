package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.EmployeeDAO;
import lk.ijse.rangabeautysalon.dao.custom.ExtraExpenseDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.ExtraExpenseDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ExtraExpenseServiceImpl implements ExtraExpenseService{
    private final ExtraExpenseDAO extraExpenseDAO;
    private final Convertor convertor;
    private final Connection connection;

    public ExtraExpenseServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        extraExpenseDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.EXTRAEXPENSE );
        convertor=new Convertor();
    }

    @Override
    public boolean save(ExtraExpenseDTO extraExpenseDTO) throws SQLException, ClassNotFoundException {
        Boolean save = extraExpenseDAO.save(convertor.toExtraExpense(extraExpenseDTO));
        return save;
    }

    @Override
    public boolean update(ExtraExpenseDTO extraExpenseDTO) throws SQLException, ClassNotFoundException {
        Boolean update = extraExpenseDAO.update(convertor.toExtraExpense(extraExpenseDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = extraExpenseDAO.delete(id);
        return delete;
    }

    @Override
    public ExtraExpenseDTO search(String id) throws SQLException, ClassNotFoundException {
        ExtraExpenseDTO extraExpenseDTO = convertor.fromExtraExpense(extraExpenseDAO.search(id));
        return extraExpenseDTO;
    }

    @Override
    public ArrayList<ExtraExpenseDTO> getAllExtraExpenses() throws ClassNotFoundException, SQLException {
        return (ArrayList<ExtraExpenseDTO>) extraExpenseDAO.getAllExtraExpenses().stream().map(extraExpenses -> convertor.fromExtraExpense(extraExpenses)).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> loadExtraExpenseIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = extraExpenseDAO.loadExtraExpenseIds();
        return strings;
    }

    @Override
    public String generateNextExExpenseId() throws SQLException, ClassNotFoundException {
        String expenseId = extraExpenseDAO.generateNextExExpenseId();
        return expenseId;
    }
}
