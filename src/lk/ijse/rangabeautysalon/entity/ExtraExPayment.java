package lk.ijse.rangabeautysalon.entity;

public class ExtraExPayment  implements  SuperEntity{
    private String ExId;
    private String PaymentId;
    private double cost;

    public ExtraExPayment() {
    }

    public ExtraExPayment(String exId, String paymentId, double cost) {
        ExId = exId;
        PaymentId = paymentId;
        this.cost = cost;
    }

    public String getExId() {
        return ExId;
    }

    public void setExId(String exId) {
        ExId = exId;
    }

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ExtraExPayment{" +
                "ExId='" + ExId + '\'' +
                ", PaymentId='" + PaymentId + '\'' +
                ", cost=" + cost +
                '}';
    }
}
