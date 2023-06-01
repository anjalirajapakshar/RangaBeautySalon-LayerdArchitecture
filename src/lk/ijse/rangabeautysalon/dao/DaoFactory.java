package lk.ijse.rangabeautysalon.dao;

import lk.ijse.rangabeautysalon.dao.custom.impl.*;

import java.sql.Connection;

public class DaoFactory {

    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Connection connection, DaoTypes daoType) {
        switch (daoType){
            case APPOINTMENT:
                return (T)new AppointmentImpl(connection);

            case CUSTOMER:
                return (T)new CustomerImpl(connection);

            case DRESSINGAPPOINTMENT:
                return (T)new DressingAppointmentImpl(connection);

            case DRESSING:
                return (T) new DressingImpl(connection);

            case EMPLOYEE:
                return (T)new EmployeeImpl(connection);

            case EXTRAEXPENSE:
                return (T)new ExtraExpenseImpl(connection);

            case ORDER:
                return (T)new OrderImpl(connection);

            case ORDERPRODUCT:
                return (T)new OrderProductImpl(connection);

            case PAYMENT:
                return (T)new PaymentImpl(connection);

            case PRODUCT:
                return (T)new ProductImpl(connection);

            case REGISTRATION:
                return (T)new RegistrationImpl(connection);

            case SALONSAPPOINTMENT:
                return (T)new SalonSAppointmentImpl(connection);

            case SALONSERVICE:
                return (T)new SalonServiceImpl(connection);

            case APPOINTMENTTM:
                return (T)new AppointmentTMImpl(connection);

            default:
                return null;

        }

    }
}
