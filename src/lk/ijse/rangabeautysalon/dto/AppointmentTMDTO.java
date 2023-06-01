package lk.ijse.rangabeautysalon.dto;

public class AppointmentTMDTO {
    private String AppId;
    private String CID;
    private String Description;
    private String SalonService;
    private String DressingId;
    private String date;
    private String time;

    public AppointmentTMDTO() {
    }

    public AppointmentTMDTO(String appId, String CID, String description, String salonService, String dressingId, String date, String time) {
        AppId = appId;
        this.CID = CID;
        Description = description;
        SalonService = salonService;
        DressingId = dressingId;
        this.date = date;
        this.time = time;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSalonService() {
        return SalonService;
    }

    public void setSalonService(String salonService) {
        SalonService = salonService;
    }

    public String getDressingId() {
        return DressingId;
    }

    public void setDressingId(String dressingId) {
        DressingId = dressingId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "AppointmentTM{" +
                "AppId='" + AppId + '\'' +
                ", CID='" + CID + '\'' +
                ", Description='" + Description + '\'' +
                ", SalonService='" + SalonService + '\'' +
                ", DressingId='" + DressingId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
