package lk.ijse.rangabeautysalon.dto;

public class BillDTO {
    private String Bid;
    private String description;
    private double amount;
    private String date;
    private String paymentId;

    public BillDTO() {
    }

    public BillDTO(String bid, String description, double amount, String date, String paymentId) {
        Bid = bid;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.paymentId = paymentId;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "Bid='" + Bid + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}
