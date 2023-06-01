package lk.ijse.rangabeautysalon.dto;

public class DressingDTO {
    private String DressingId;
    private String type;
    private double cost;

    public DressingDTO() {
    }

    public DressingDTO(String dressingId, String type, double cost) {
        DressingId = dressingId;
        this.type = type;
        this.cost = cost;
    }

    public String getDressingId() {
        return DressingId;
    }

    public void setDressingId(String dressingId) {
        DressingId = dressingId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Dressing{" +
                "DressingId='" + DressingId + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                '}';
    }
}
