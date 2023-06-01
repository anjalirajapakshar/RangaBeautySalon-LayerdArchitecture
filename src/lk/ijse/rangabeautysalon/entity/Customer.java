package lk.ijse.rangabeautysalon.entity;

public class Customer  implements  SuperEntity{
    private String CustID;
    private String name;
    private String address;
    private int tel;
    private String nic;

    public Customer() {}

    public Customer(String custID, String name, String address, int tel, String nic) {
        this.CustID = custID;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.nic = nic;
    }

    public String getCustID() {
        return CustID;
    }

    public void setCustID(String custID) {
        CustID = custID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustID='" + CustID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel=" + tel +
                ", nic=" + nic +
                '}';
    }
}
