package lk.ijse.rangabeautysalon.dto;

public class OrderProductDetailDTO {
    private String OrderId;
    private String ProductId;
    private String date;
    private int qty;

    public OrderProductDetailDTO() {
    }

    public OrderProductDetailDTO(String orderId, String productId, String date, int qty) {
        OrderId = orderId;
        ProductId = productId;
        this.date = date;
        this.qty = qty;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderProductDetail{" +
                "OrderId='" + OrderId + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", date='" + date + '\'' +
                ", qty=" + qty +
                '}';
    }
}
