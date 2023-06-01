package lk.ijse.rangabeautysalon.service.util;

import lk.ijse.rangabeautysalon.dto.*;
import lk.ijse.rangabeautysalon.entity.*;

public class Convertor {

    public RegistrationDTO fromRegistration(Registration registration){
        return new RegistrationDTO(registration.getUserName(),registration.getPassword(),registration.getRole());
    }

    public Registration toRegistration(RegistrationDTO registrationDTO){
        return new Registration(registrationDTO.getUserName(),registrationDTO.getPassword(),registrationDTO.getRole());
    }

    public AppointmentDTO fromAppointment(Appointment appointment){
        return new AppointmentDTO(appointment.getAppId(),
                appointment.getEmpId(),
                appointment.getcId(),
                appointment.getDescription(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getCost());
    }

    public Appointment toAppointment(AppointmentDTO appointmentDTO){
        return new Appointment(appointmentDTO.getAppId(),
                appointmentDTO.getEmpId(),
                appointmentDTO.getcId(),
                appointmentDTO.getDescription(),
                appointmentDTO.getDate(),
                appointmentDTO.getTime(),
                appointmentDTO.getCost());
    }

    public BillDTO fromBill(Bill bill){
        return new BillDTO(bill.getBid(),bill.getDescription(),bill.getAmount(),bill.getDate(),bill.getPaymentId());
    }

    public Bill toBill(BillDTO billDTO){
        return new Bill(billDTO.getBid(),billDTO.getDescription(),billDTO.getAmount(),billDTO.getDate(),billDTO.getPaymentId());
    }

    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustID(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel(),
                    customer.getNic());
    }

    public Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustID(),
                    customerDTO.getName(),
                    customerDTO.getAddress(),
                    customerDTO.getTel(),
                    customerDTO.getNic());
    }

    public DressingDTO fromDressing(Dressing dressing){
        if(dressing != null){
            return new DressingDTO(dressing.getDressingId(),
                    dressing.getType(),
                    dressing.getCost());
        }else{
            return null;
        }
    }

    public Dressing toDressing(DressingDTO dressingDTO){
        if(dressingDTO != null) {
            return new Dressing(dressingDTO.getDressingId(),
                    dressingDTO.getType(),
                    dressingDTO.getCost());
        }else{
            return  null;
        }
    }

    public DressingappointmentdetailDTO fromDressingappointmentdetail(Dressingappointmentdetail dressingappointmentdetail){
        return new DressingappointmentdetailDTO(dressingappointmentdetail.getAppId(),
                    dressingappointmentdetail.getDressing_id(),
                    dressingappointmentdetail.getDate());
    }

    public Dressingappointmentdetail toDressingappointmentdetail(DressingappointmentdetailDTO dressingappointmentdetailDTO){
        return new Dressingappointmentdetail(dressingappointmentdetailDTO.getAppId(),
                    dressingappointmentdetailDTO.getDressing_id(),
                    dressingappointmentdetailDTO.getDate());
    }

    public EmployeeDTO fromEmployee(Employee employee){
        return new EmployeeDTO(employee.getEmpId(),
                employee.getName(),
                employee.getAddress(),
                employee.getTel(),
                employee.getNic());
    }

    public Employee toEmployee(EmployeeDTO employeeDTO){
        return new Employee(employeeDTO.getEmpId(),
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getTel(),
                employeeDTO.getNic());
    }

    public ExtraExPaymentDTO fromExtraExPayment(ExtraExPayment extraExPayment){
        return new ExtraExPaymentDTO(extraExPayment.getExId(),
                extraExPayment.getPaymentId(),
                extraExPayment.getCost());
    }

    public ExtraExPayment toExtraExPayment(ExtraExPaymentDTO extraExPaymentDTO){
        return new ExtraExPayment(extraExPaymentDTO.getExId(),
                extraExPaymentDTO.getPaymentId(),
                extraExPaymentDTO.getCost());
    }

    public ExtraExpenseDTO fromExtraExpense(ExtraExpense extraExpense){
        return new ExtraExpenseDTO(extraExpense.getExId(),
                extraExpense.getDesc(),
                extraExpense.getCost());
    }

    public ExtraExpense toExtraExpense(ExtraExpenseDTO extraExpenseDTO){
        return new ExtraExpense(extraExpenseDTO.getExId(),
                extraExpenseDTO.getDesc(),
                extraExpenseDTO.getCost());
    }

    public OrderDTO fromOrder(Order order){
        return new OrderDTO(order.getOrderId(),
                order.getDate(),
                order.getEmpId(),
                order.getCid(),
                order.getCost());
    }

    public Order toOrder(OrderDTO orderDTO){
        return new Order(orderDTO.getOrderId(),
                orderDTO.getDate(),
                orderDTO.getEmpId(),
                orderDTO.getCid(),
                orderDTO.getCost());
    }

    public OrderProductDetailDTO fromOrderProductDetail(OrderProductDetail orderProductDetail){
        return new OrderProductDetailDTO(orderProductDetail.getOrderId(),
                orderProductDetail.getProductId(),
                orderProductDetail.getDate(),
                orderProductDetail.getQty());
    }

    public OrderProductDetail toOrderProductDetail(OrderProductDetailDTO orderProductDetailDTO){
        return new OrderProductDetail(orderProductDetailDTO.getOrderId(),
                orderProductDetailDTO.getProductId(),
                orderProductDetailDTO.getDate(),
                orderProductDetailDTO.getQty());
    }

    public PaymentDTO fromPayment(Payment payment){
        return new PaymentDTO(payment.getPaymentId(),
                payment.getDate(),
                payment.getAmount(),
                payment.getAppId(),
                payment.getOid(),
                payment.getCid());
    }

    public Payment toPayment(PaymentDTO paymentDTO){
        return new Payment(paymentDTO.getPaymentId(),
                paymentDTO.getDate(),
                paymentDTO.getAmount(),
                paymentDTO.getAppId(),
                paymentDTO.getOid(),
                paymentDTO.getCid());
    }

    public ProductDTO fromProduct(Product product){
        return new ProductDTO(product.getProductId(),
                product.getName(),
                product.getCost(),
                product.getQtyOnHand());
    }

    public Product toProduct(ProductDTO productDTO){
        return new Product(productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getCost(),
                productDTO.getQtyOnHand());
    }

    public SalonsappointmentdetailDTO fromSalonsappointmentdetail(Salonsappointmentdetail salonsappointmentdetail){
        return new SalonsappointmentdetailDTO(salonsappointmentdetail.getAppId(),
                    salonsappointmentdetail.getSSId(),
                    salonsappointmentdetail.getDate());
    }

    public Salonsappointmentdetail toSalonsappointmentdetail(SalonsappointmentdetailDTO salonsappointmentdetailDTO){
        return new Salonsappointmentdetail(salonsappointmentdetailDTO.getAppId(),
                    salonsappointmentdetailDTO.getSSId(),
                    salonsappointmentdetailDTO.getDate());
    }

    public SalonServiceDTO fromSalonService(SalonService salonService){
        if(salonService != null){
            return new SalonServiceDTO(salonService.getSSId(),
                    salonService.getServiceName(),
                    salonService.getCost());
        }else{
            return null;
        }
    }

    public SalonService toSalonService(SalonServiceDTO salonServiceDTO){
        if(salonServiceDTO != null) {
            return new SalonService(salonServiceDTO.getSSId(),
                    salonServiceDTO.getServiceName(),
                    salonServiceDTO.getCost());
        }else{
            return null;
        }
    }

    public AppointmentTMDTO fromAppointmentTM(AppointmentTM appointmentTM){
        return new AppointmentTMDTO(appointmentTM.getAppId(),
                appointmentTM.getCID(),
                appointmentTM.getDescription(),
                appointmentTM.getSalonService(),
                appointmentTM.getDressingId(),
                appointmentTM.getDate(),
                appointmentTM.getTime());
    }

    public AppointmentTM toAppointmentTM(AppointmentTMDTO appointmentTMDTO){
        return new AppointmentTM(appointmentTMDTO.getAppId(),
                appointmentTMDTO.getCID(),
                appointmentTMDTO.getDescription(),
                appointmentTMDTO.getSalonService(),
                appointmentTMDTO.getDressingId(),
                appointmentTMDTO.getDate(),
                appointmentTMDTO.getTime());
    }

    public PaymentTMDTO fromPaymentTM(PaymentTM paymentTM){
        return new PaymentTMDTO(paymentTM.getCode(),
                paymentTM.getDescription(),
                paymentTM.getAppId(),
                paymentTM.getOrderId(),
                paymentTM.getExPayId(),
                paymentTM.getAmount());
    }

    public PaymentTM toPaymentTM(PaymentTMDTO paymentTMDTO){
        return new PaymentTM(paymentTMDTO.getCode(),
                paymentTMDTO.getDescription(),
                paymentTMDTO.getAppId(),
                paymentTMDTO.getOrderId(),
                paymentTMDTO.getExPayId(),
                paymentTMDTO.getAmount());
    }
}
