package lk.ijse.rangabeautysalon.service;

import lk.ijse.rangabeautysalon.service.custom.Impl.*;

import java.sql.SQLException;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceTypes) throws SQLException, ClassNotFoundException {
        switch (serviceTypes){
            case APPOINTMENT:
                return (T) new AppointmentSeviceImpl();

            case CUSTOMER:
                return (T)new CustomerServiceImpl();

            case DRESSINGAPPOINTMENT:
                return (T)new DressingAppointmentServiceImpl();

            case DRESSING:
                return (T) new DressingServiceImpl();

            case EMPLOYEE:
                return (T)new EmployeeServiceImpl();

            case EXTRAEXPENSE:
                return (T)new ExtraExpenseServiceImpl();

            case ORDER:
                return (T)new OrderServiceImpl();

            case ORDERPRODUCT:
                return (T)new OrderProductServiceImpl();

            case PAYMENT:
                return (T)new PaymentServiceImpl();

            case PRODUCT:
                return (T)new ProductServiceImpl();

            case REGISTRATION:
                return (T)new RegistrationServiceImpl();

            case SALONSAPPOINTMENT:
                return (T)new SalonSAppointmentServiceImpl();

            case SALONSERVICE:
                return (T)new SalonSServiceImpl();

            case APPOINTMENTTM:
                return (T)new AppointmentTMServiceImpl();

            default:
                return null;
        }
    }

}
