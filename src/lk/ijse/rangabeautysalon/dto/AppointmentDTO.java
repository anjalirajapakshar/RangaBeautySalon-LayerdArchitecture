package lk.ijse.rangabeautysalon.dto;

public class AppointmentDTO {
    private String appId;
    private String empId;
    private String cId;
    private String description;
    private String date;
    private String time;
    private double cost;

    public AppointmentDTO() {
    }

    public AppointmentDTO(String appId, String empId, String cId, String description, String date, String time, double cost) {
        this.appId = appId;
        this.empId = empId;
        this.cId = cId;
        this.description = description;
        this.date = date;
        this.time = time;
        this.cost = cost;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appId='" + appId + '\'' +
                ", empId='" + empId + '\'' +
                ", cId='" + cId + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cost=" + cost +
                '}';
    }
}
