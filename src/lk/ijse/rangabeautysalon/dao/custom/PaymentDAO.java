package lk.ijse.rangabeautysalon.dao.custom;

import lk.ijse.rangabeautysalon.dao.CrudDAO;
import lk.ijse.rangabeautysalon.entity.Payment;
import lk.ijse.rangabeautysalon.entity.PaymentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Boolean, Payment> {
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public Payment search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<Payment> getAllPayments() throws ClassNotFoundException, SQLException;
    public ArrayList<PaymentTM> getAllPaymentTMs() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadPaymentIds() throws SQLException, ClassNotFoundException;
    public boolean savePaymentTM(PaymentTM paymentTM) throws SQLException, ClassNotFoundException ;
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException ;
    public String generateNextcodeId() throws SQLException, ClassNotFoundException ;
}
