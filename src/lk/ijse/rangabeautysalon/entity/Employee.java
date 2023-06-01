package lk.ijse.rangabeautysalon.entity;

public class Employee  implements  SuperEntity{
    private String EmpId;
    private String name;
    private String address;
    private int tel;
    private String nic;

    public Employee() {
    }

    public Employee(String empId, String name, String address, int tel, String nic) {
        EmpId = empId;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.nic = nic;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
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
        return "Employee{" +
                "EmpId='" + EmpId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel=" + tel +
                ", nic=" + nic +
                '}';
    }
}
