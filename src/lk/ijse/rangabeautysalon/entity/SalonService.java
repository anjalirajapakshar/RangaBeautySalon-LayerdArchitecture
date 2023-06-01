package lk.ijse.rangabeautysalon.entity;

public class SalonService  implements  SuperEntity{
    private String SSId;
    private String ServiceName;
    private double cost;

    public SalonService() {
    }

    public SalonService(String SSId, String serviceName, double cost) {
        this.SSId = SSId;
        ServiceName = serviceName;
        this.cost = cost;
    }

    public String getSSId() {
        return SSId;
    }

    public void setSSId(String SSId) {
        this.SSId = SSId;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "SalonService{" +
                "SSId='" + SSId + '\'' +
                ", ServiceName='" + ServiceName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
