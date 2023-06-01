package lk.ijse.rangabeautysalon.service.custom;

import lk.ijse.rangabeautysalon.dto.PaymentDTO;
import lk.ijse.rangabeautysalon.dto.PaymentTMDTO;
import lk.ijse.rangabeautysalon.service.SuperService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentService extends SuperService {
    public boolean save(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    public boolean update(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws ClassNotFoundException, SQLException;
    public PaymentDTO search(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<PaymentDTO> getAllPayments() throws ClassNotFoundException, SQLException;
    public ArrayList<PaymentTMDTO> getAllPaymentTMs() throws ClassNotFoundException, SQLException;
    public ArrayList<String> loadPaymentIds() throws SQLException, ClassNotFoundException;
    public boolean savePaymentTM(PaymentTMDTO paymentTMDTO) throws SQLException, ClassNotFoundException ;
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException ;
    public String generateNextcodeId() throws SQLException, ClassNotFoundException ;
}
