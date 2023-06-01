package lk.ijse.rangabeautysalon.controller.supplier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.rangabeautysalon.dto.OrderDTO;
import lk.ijse.rangabeautysalon.dto.PaymentDTO;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageSupplierFormController implements Initializable {
    public TableView<OrderDTO> tblOrderDetail;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colEmplyeeid;
    public TableColumn colCustomerId;
    public TableView<PaymentDTO> tblPaymentDetail;
    public TableColumn colPaymentId;
    public TableColumn colDate;
    public TableColumn colAmount;
    public TableColumn colAppId;
    public TableColumn colPaymentOrderId;
    public TableColumn colPaymentCustomerId;

    public OrderService orderService;
    public PaymentService paymentService;

    public void loadOrderTableDetails(){
        try {
            ArrayList<OrderDTO> allOrders = orderService.getAllOrders();
            ObservableList<OrderDTO> orders = FXCollections.observableArrayList();
            for(OrderDTO o : allOrders){
                OrderDTO order = new OrderDTO(o.getOrderId(),o.getDate(),o.getEmpId(),o.getCid(),o.getCost());
                orders.add(order);
            }
            tblOrderDetail.setItems(orders);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadPaymentTableDetails(){
        try {
            ArrayList<PaymentDTO> allOrders = paymentService.getAllPayments();
            ObservableList<PaymentDTO> orders = FXCollections.observableArrayList();
            for(PaymentDTO o : allOrders){
                PaymentDTO order = new PaymentDTO(o.getPaymentId(),o.getDate(),o.getAmount(),o.getAppId(),o.getOid(),o.getCid());
                orders.add(order);
            }
            tblPaymentDetail.setItems(orders);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colOrderId.setCellValueFactory(new PropertyValueFactory("OrderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory("date"));
        colEmplyeeid.setCellValueFactory(new PropertyValueFactory("empId"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory("cid"));

        try {
            this.orderService= ServiceFactory.getInstance().getService(ServiceTypes.ORDER);
            this.paymentService= ServiceFactory.getInstance().getService(ServiceTypes.PAYMENT);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        loadOrderTableDetails();
        loadPaymentTableDetails();
    }

}
