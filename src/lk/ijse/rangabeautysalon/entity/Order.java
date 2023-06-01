package lk.ijse.rangabeautysalon.entity;

public class Order  implements  SuperEntity{
    private String OrderId;
    private String date;
    private String empId;
    private String cid;
    private double cost;

    public Order() {
    }

    public Order(String orderId, String date, String empId, String cid, double cost) {
        OrderId = orderId;
        this.date = date;
        this.empId = empId;
        this.cid = cid;
        this.cost = cost;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId='" + OrderId + '\'' +
                ", date='" + date + '\'' +
                ", empId='" + empId + '\'' +
                ", cid='" + cid + '\'' +
                ", cost=" + cost +
                '}';
    }
}
