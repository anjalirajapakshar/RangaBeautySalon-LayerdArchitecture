package lk.ijse.rangabeautysalon.dto;

public class PaymentTMDTO {
    private String code;
    private String description;
    private String appId;
    private String orderId;
    private String ExPayId;
    private double amount;

    public PaymentTMDTO() {
    }

    public PaymentTMDTO(String code, String description, String appId, String orderId, String exPayId, double amount) {
        this.code = code;
        this.description = description;
        this.appId = appId;
        this.orderId = orderId;
        ExPayId = exPayId;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getExPayId() {
        return ExPayId;
    }

    public void setExPayId(String exPayId) {
        ExPayId = exPayId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", appId='" + appId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", ExPayId='" + ExPayId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
