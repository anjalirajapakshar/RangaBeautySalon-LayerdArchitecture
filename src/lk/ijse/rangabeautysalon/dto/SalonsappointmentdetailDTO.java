package lk.ijse.rangabeautysalon.dto;

public class SalonsappointmentdetailDTO {
    private String appId;
    private String SSId;
    private String date;

    public SalonsappointmentdetailDTO() {
    }

    public SalonsappointmentdetailDTO(String appId, String SSId, String date) {
        this.appId = appId;
        this.SSId = SSId;
        this.date = date;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSSId() {
        return SSId;
    }

    public void setSSId(String SSId) {
        this.SSId = SSId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SalonsAppointmentDetail{" +
                "appId='" + appId + '\'' +
                ", SSId='" + SSId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
