package lk.ijse.rangabeautysalon.dao.custom.impl;

import lk.ijse.rangabeautysalon.dao.custom.PaymentDAO;
import lk.ijse.rangabeautysalon.dao.exceptions.ConstraintViolationException;
import lk.ijse.rangabeautysalon.dao.util.CrudUtil;
import lk.ijse.rangabeautysalon.entity.Payment;
import lk.ijse.rangabeautysalon.entity.PaymentTM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentImpl implements PaymentDAO {
    private final Connection connection;

    public PaymentImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public Boolean save(Payment payment) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO paymentdetail VALUES(?, ?, ?, ?, ?, ?)",
                payment.getPaymentId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getAppId(),
                payment.getOid(),
                payment.getCid()
        );
    }

    @Override
    public Boolean update(Payment payment) throws ConstraintViolationException, SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public Payment search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Payment> getAllPayments() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * From paymentdetail");
        ArrayList<Payment>PaymentTMList=new ArrayList<>();
        while(result.next()){
            Payment payment = new Payment(result.getString("PaymentId"),result.getString("date"), result.getDouble("amount"), result.getString("appId"), result.getString("Oid"),result.getString("cid"));
            PaymentTMList.add(payment);
        }
        return PaymentTMList;
    }

    @Override
    public ArrayList<PaymentTM> getAllPaymentTMs() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * From paymenttm");
        ArrayList<PaymentTM>PaymentTMList=new ArrayList<>();
        while(result.next()){
            PaymentTM payment = new PaymentTM(result.getString("code"),result.getString("description"), result.getString("appId"), result.getString("orderId"), result.getString("ExPayId"),result.getDouble("amount"));
            PaymentTMList.add(payment);
        }
        return PaymentTMList;
    }

    @Override
    public ArrayList<String> loadPaymentIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean savePaymentTM(PaymentTM paymentTM) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO paymenttm VALUES(?, ?, ?, ?, ?, ?)",
                paymentTM.getCode(),
                paymentTM.getDescription(),
                paymentTM.getAppId(),
                paymentTM.getOrderId(),
                paymentTM.getExPayId(),
                paymentTM.getAmount()
        );
    }


    @Override
    public String generateNextPaymentId() throws SQLException, ClassNotFoundException {
        String SQL="SELECT PaymentId from paymentdetail order by PaymentId desc limit 1";
        ResultSet result = CrudUtil.execute(SQL);
        if(result.next()){
            return generateNextPaymentId(result.getString(1));
        }
        return generateNextPaymentId(result.getString(null));
    }

    private static String generateNextPaymentId(String CurrentgenerateNextPaymentId){
        if(CurrentgenerateNextPaymentId != null){
            String[] split = CurrentgenerateNextPaymentId.split("P");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "P" + id;
        }
        return "P1";
    }

    @Override
    public String generateNextcodeId() throws SQLException, ClassNotFoundException {
        String SQL="SELECT code from paymenttm order by code desc limit 1";
        ResultSet result = CrudUtil.execute(SQL);
        if(result.next()){
            return generateNextcodeId(result.getString(1));
        }
        return generateNextcodeId(result.getString(null));
    }

    private static String generateNextcodeId(String CurrentAppId){
        if(CurrentAppId != null){
            String[] split = CurrentAppId.split("Code");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "Code" + id;
        }
        return "Code1";
    }
}
