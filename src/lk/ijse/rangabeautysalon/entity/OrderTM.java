package lk.ijse.rangabeautysalon.entity;

public class OrderTM   implements  SuperEntity{
    private String ProductId;
    private String Description;
    private int qty;
    private double UnitPrice;
    private double TotalCost;

    public OrderTM() {
    }

    public OrderTM(String productId, String description, int qty, double unitPrice, double totalCost) {
        ProductId = productId;
        Description = description;
        this.qty = qty;
        UnitPrice = unitPrice;
        TotalCost = totalCost;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "ProductId='" + ProductId + '\'' +
                ", Description='" + Description + '\'' +
                ", qty=" + qty +
                ", UnitPrice=" + UnitPrice +
                ", TotalCost=" + TotalCost +
                '}';
    }
}
