package lk.ijse.rangabeautysalon.entity;

public class Dressingappointmentdetail  implements  SuperEntity{
    private String appId;
    private String dressing_id;
    private String date;

    public Dressingappointmentdetail() {
    }

    public Dressingappointmentdetail(String appId, String dressing_id, String date) {
        this.appId = appId;
        this.dressing_id = dressing_id;
        this.date = date;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDressing_id() {
        return dressing_id;
    }

    public void setDressing_id(String dressing_id) {
        this.dressing_id = dressing_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DressingAppointmentDetail{" +
                "appId='" + appId + '\'' +
                ", dressing_id='" + dressing_id + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
