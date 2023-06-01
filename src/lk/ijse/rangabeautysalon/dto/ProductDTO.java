package lk.ijse.rangabeautysalon.dto;

public class ProductDTO {
    private String productId;
    private String name;
    private double cost;
    private int qtyOnHand;

    public ProductDTO() {
    }

    public ProductDTO(String productId, String name, double cost, int qtyOnHand) {
        this.productId = productId;
        this.name = name;
        this.cost = cost;
        this.qtyOnHand = qtyOnHand;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", qtyOnHand=" + qtyOnHand +
                '}';
    }
}
