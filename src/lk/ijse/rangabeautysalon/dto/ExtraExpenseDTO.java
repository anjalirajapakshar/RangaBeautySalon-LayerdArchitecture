package lk.ijse.rangabeautysalon.dto;

public class ExtraExpenseDTO {
    private String ExId;
    private String Desc;
    private double cost;

    public ExtraExpenseDTO() {
    }

    public ExtraExpenseDTO(String exId, String desc, double cost) {
        ExId = exId;
        Desc = desc;
        this.cost = cost;
    }

    public String getExId() {
        return ExId;
    }

    public void setExId(String exId) {
        ExId = exId;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ExtraExpense{" +
                "ExId='" + ExId + '\'' +
                ", Desc='" + Desc + '\'' +
                ", cost=" + cost +
                '}';
    }
}
