package lk.ijse.rangabeautysalon.dto;

public class RegistrationDTO {
    private int regId;
    private String userName;
    private String password;
    private String role;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int regId, String userName, String password, String role) {
        this.regId = regId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public RegistrationDTO(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regId=" + regId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
