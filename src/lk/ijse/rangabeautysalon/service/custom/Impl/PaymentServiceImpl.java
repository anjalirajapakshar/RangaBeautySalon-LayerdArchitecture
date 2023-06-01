package lk.ijse.rangabeautysalon.service.custom.Impl;

import lk.ijse.rangabeautysalon.dao.DaoFactory;
import lk.ijse.rangabeautysalon.dao.DaoTypes;
import lk.ijse.rangabeautysalon.dao.custom.OrderDAO;
import lk.ijse.rangabeautysalon.dao.custom.PaymentDAO;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.PaymentDTO;
import lk.ijse.rangabeautysalon.dto.PaymentTMDTO;
import lk.ijse.rangabeautysalon.service.util.Convertor;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService{
    private final PaymentDAO paymentDAO;
    private final Convertor convertor;
    private final Connection connection;

    public PaymentServiceImpl() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        paymentDAO= DaoFactory.getInstance().getDAO(connection, DaoTypes.PAYMENT );
        convertor=new Convertor();
    }

    @Override
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        Boolean save = paymentDAO.save(convertor.toPayment(paymentDTO));
        return save;
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        Boolean update = paymentDAO.update(convertor.toPayment(paymentDTO));
        return update;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean delete = paymentDAO.delete(id);
        return delete;
    }

    @Override
    public PaymentDTO search(String id) throws SQLException, ClassNotFoundException {
        PaymentDTO paymentDTO = convertor.fromPayment(paymentDAO.search(id));
        return paymentDTO;
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws ClassNotFoundException, SQLException {
        return (ArrayList<PaymentDTO>) paymentDAO.getAllPayments().stream().map(payments -> convertor.fromPayment(payments)).collect(Collectors.toList());

    }

    @Override
    public ArrayList<PaymentTMDTO> getAllPaymentTMs() throws ClassNotFoundException, SQLException {
        return (ArrayList<PaymentTMDTO>) paymentDAO.getAllPaymentTMs().stream().map(payments -> convertor.fromPaymentTM(payments)).collect(Collectors.toList());

    }

    @Override
    public ArrayList<String> loadPaymentIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> strings = paymentDAO.loadPaymentIds();
        return strings;
    }

    @Override
    public boolean savePaymentTM(PaymentTMDTO paymentTMDTO) throws SQLException, ClassNotFoundException {
        Boolean save = paymentDAO.savePaymentTM(convertor.toPaymentTM(paymentTMDTO));
        return save;
    }

    @Override
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        String s = paymentDAO.generateNextPaymentId();
        return s;
    }

    @Override
    public String generateNextcodeId() throws SQLException, ClassNotFoundException {
        String s = paymentDAO.generateNextcodeId();
        return s;
    }
}
