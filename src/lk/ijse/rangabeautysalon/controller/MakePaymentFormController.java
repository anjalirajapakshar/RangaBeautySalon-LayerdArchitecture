package lk.ijse.rangabeautysalon.controller;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rangabeautysalon.controller.appointment.ManageAppointmentFormController;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.*;
import lk.ijse.rangabeautysalon.entity.Appointment;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakePaymentFormController implements Initializable {
    public AnchorPane pane;
    public JFXDatePicker datePicker;
    public Label lblPaymentId;
    public Label lblCustName;
    public JFXTextField txtCustId;
    public JFXTextField txtDescription;
    public TableView tblPayment;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colAppId;
    public TableColumn colOrderId;
    public Label lblTotalCost;
    public JFXTextField txtAppointmentId;
    public JFXTextField txtOrderId;
    public JFXTextField txtExtraPaymentId;
    public TableColumn colExPayId;
    public TableColumn colAmount;
    public JFXTextField txtExPayId;
    public Label lblOrderAppointmentDetails;

    public PaymentService paymentService;
    public CustomerService customerService;
    public AppointmentService appointmentService;
    public OrderService orderService;
    public ExtraExpenseService extraExpenseService;


    public void btnExtraPaymentOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/rangabeautysalon/view/ExtraPaymentForm.fxml"));
        Parent load = fxmlLoader.load();
        Stage stage=new Stage();
        stage.setTitle("Extra Expense Form");
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) {

    }

    public void btnDoPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentIdText = lblPaymentId.getText();
        String custIdText = txtCustId.getText();
        String custNameText = lblCustName.getText();
        String date = String.valueOf(datePicker.getValue());
        String descriptionText = txtDescription.getText();
        //---------------------------------------------------------------
        String appointmentIdText = txtAppointmentId.getText();
        System.out.println(appointmentIdText);
        if(appointmentIdText.equalsIgnoreCase("")){
            appointmentIdText=null;
        }
        String orderIdText = txtOrderId.getText();
        System.out.println(orderIdText);
        if(orderIdText.equalsIgnoreCase("")){
            orderIdText=null;
        }
        String exPayIdText = txtExPayId.getText();
        System.out.println(exPayIdText);
        if(exPayIdText.equalsIgnoreCase("")){
            exPayIdText=null;
        }
        //---------------------------------------------------------------
        double ExPaycost = txtExPayIdOnAction(actionEvent);
        double AppointmentCost = txtAppointmentIdOnAction(actionEvent);
        double OrderCost = txtOrderIdOnAction(actionEvent);

        double totcost=ExPaycost+AppointmentCost+OrderCost;
        String nextcodeId = loadNextcodeId();
        PaymentDTO payment = new PaymentDTO(paymentIdText,date,totcost,appointmentIdText,orderIdText,custIdText);
        PaymentTMDTO paymentTM = new PaymentTMDTO(nextcodeId,descriptionText,appointmentIdText,orderIdText,exPayIdText,totcost);

        boolean savePayment = paymentService.save(payment);
        System.out.println(savePayment);
        boolean savePaymentTM = paymentService.savePaymentTM(paymentTM);
        System.out.println(savePaymentTM);

        lblTotalCost.setText(String.valueOf(totcost));

        if(savePayment ){
            if(savePaymentTM) {
                new Alert(Alert.AlertType.CONFIRMATION, "Paid !!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Payment Unsucsessfull !!").show();
        }

        loadPaymentTableDetails();
    }

    public String loadNextcodeId(){
        String nextCodeId="";
        try {
            nextCodeId = paymentService.generateNextcodeId();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nextCodeId;
    }

    public void btnGenerateBillOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jasdi = JRXmlLoader.load("D:\\SWE PRACTICLE\\RangaBeautySalon\\src\\lk\\ijse" +
                    "\\rangabeautysalon\\report\\Blank_A4_1.jrxml");
            String sql = "Select pd.paymentId,opd.oid,opd.productId,p.name,p.cost as unit_price,opd.qty from paymentdetail pd inner join orderproductdetail " +
                    "opd on pd.Oid = opd.Oid inner join product p on opd.productId= p.productId \n" +
                    "UNION" +
                    "select pd.paymentId,sad.appid,sad.SS_Id,ss.Sname,ss.cost,1 as qty from paymentdetail pd inner join salonsappointmentdetail sad on pd.appid = sad.appId" +
                    " inner join salonservice ss on sad.SS_Id = ss.SS_Id" +
                    "UNION" +
                    "select pd.paymentId,dad.appid,dad.dressing_Id,d.type,d.cost,1 as qty from paymentdetail pd inner join dressingappointmentdetail dad on " +
                    "pd.appid = dad.appId inner join dressing d on dad.dressing_id = d.dressing_id where paymentId = 'P1'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jasdi.setQuery(newQuery);

            JasperReport js = JasperCompileManager.compileReport(jasdi);
            JasperPrint jp = JasperFillManager.fillReport(js,null, DBConnection.getInstance().getConnection());
            JasperViewer view = new JasperViewer(jp,false);
            view.show();
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadNextAppId(){
        try {
            String nextPaymentId = paymentService.generateNextPaymentId();
            lblPaymentId.setText(nextPaymentId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colExPayId.setCellValueFactory(new PropertyValueFactory<>("ExPayId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));


        try {
            this.orderService= ServiceFactory.getInstance().getService(ServiceTypes.ORDER);
            this.paymentService= ServiceFactory.getInstance().getService(ServiceTypes.PAYMENT);
            this.appointmentService= ServiceFactory.getInstance().getService(ServiceTypes.APPOINTMENT);
            this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
            this.extraExpenseService= ServiceFactory.getInstance().getService(ServiceTypes.EXTRAEXPENSE);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ManageAppointmentFormController manageAppointmentFormController = new ManageAppointmentFormController();
        AppointmentDTO appointment = manageAppointmentFormController.getAppointment();
        if(appointment != null){
            txtAppointmentId.setText(appointment.getAppId());
        }

        MakeOrderFormController makeOrderFormController = new MakeOrderFormController();
        OrderDTO order = makeOrderFormController.getOrder();
        if(order != null){
            txtOrderId.setText(order.getOrderId());
        }

        loadNextAppId();
        loadPaymentTableDetails();
    }

    public void loadPaymentTableDetails(){
        try {
            ArrayList<PaymentTMDTO> allPaymentTMs = paymentService.getAllPaymentTMs();
            ObservableList<PaymentTMDTO> PaymentTMs = FXCollections.observableArrayList();
            for(PaymentTMDTO atm : allPaymentTMs){
                PaymentTMDTO paymentTM=new PaymentTMDTO(atm.getCode(),atm.getDescription(),atm.getAppId(),atm.getOrderId(),atm.getExPayId(),atm.getAmount());
                PaymentTMs.add(paymentTM);
            }
            tblPayment.setItems(PaymentTMs);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /*public static boolean updateAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("Update appointment set empid=?, cid=?, description=?, app_date=?, app_time=?, cost=? where appId=?",
                appointment.getEmpId(),
                appointment.getcId(),
                appointment.getDescription(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getCost(),
                appointment.getAppId()

        );*/

    public void txtCustIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String idText = txtCustId.getText();
        CustomerDTO customer = customerService.search(idText);
        if(customer != null) {
            lblCustName.setText(customer.getName());
        }else{
            new Alert(Alert.AlertType.ERROR,"Cannot find Customer !!").show();
        }
    }

    public double txtExPayIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String txtExPayIdText = txtExPayId.getText();
        ExtraExpenseDTO extraExpense = extraExpenseService.search(txtExPayIdText);
        double cost=0;
        if(extraExpense != null){
            cost = extraExpense.getCost();
        }else{
            cost=0;
        }
        return cost;
    }

    public double txtOrderIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String txtOrderIdText = txtOrderId.getText();
        OrderDTO order = orderService.search(txtOrderIdText);
        double cost=0;
        if(order != null){
            cost = order.getCost();
        }else{
            cost=0;
        }
        return cost;
    }

    public double txtAppointmentIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String appointmentIdText = txtAppointmentId.getText();
        AppointmentDTO appointment = appointmentService.search(appointmentIdText);
        double cost=0;
        if(appointment != null){
            cost = appointment.getCost();
        }else{
            cost=0;
        }
        return cost;
    }

    /*public void getExtraExDetails(ExtraExpense extraExpense){
        ExtraExpense ex=extraExpense;
        ArrayList<ExtraExpense> ExtraExpenseList=new ArrayList<>();
        ExtraExpenseList.add(ex);
        ObservableList<ExtraExpense> ExtraExpenseOblist = FXCollections.observableArrayList();
        ExtraExpenseOblist.add(ex);
        tblPayment.setItems(ExtraExpenseOblist);
    }*/


}
