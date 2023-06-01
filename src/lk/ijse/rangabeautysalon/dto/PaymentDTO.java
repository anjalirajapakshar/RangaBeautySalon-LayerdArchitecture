package lk.ijse.rangabeautysalon.dto;

public class PaymentDTO {
    private String PaymentId;
    private String date;
    private double amount;
    private String appId;
    private String Oid;
    private String cid;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymentId, String date, double amount, String appId, String oid, String cid) {
        PaymentId = paymentId;
        this.date = date;
        this.amount = amount;
        this.appId = appId;
        Oid = oid;
        this.cid = cid;
    }

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PaymentId='" + PaymentId + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", appId='" + appId + '\'' +
                ", Oid='" + Oid + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }
}
