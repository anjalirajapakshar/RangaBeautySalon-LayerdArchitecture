package lk.ijse.rangabeautysalon.controller.appointment;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.*;
import lk.ijse.rangabeautysalon.entity.*;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;
import lk.ijse.rangabeautysalon.util.Navigation;
import lk.ijse.rangabeautysalon.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ManageAppointmentFormController  implements Initializable {

    public AnchorPane pane;
    public TableView<AppointmentTMDTO> tblAppointment;
    public TableColumn colAppId;
    public TableColumn colCid;
    public TableColumn colDescription;
    public TableColumn colSalonService;
    public TableColumn colDressing;
    public TableColumn colDate;
    public TableColumn colTime;
    public JFXTextField txtDesc;
    public JFXTextField txtDate;
    public TextField txtSearchAppointment;
    public ComboBox cmbSalonService;
    public ComboBox cmbDressing;
    public Label lblAppointmentId;
    public Label lblEmployeeId;
    public ComboBox cmbCustomerIds;
    public Label lblTotalCost;
    public JFXTextField txtTime;

    public AppointmentService appointmentService;
    public CustomerService customerService;
    public SalonSService salonSService;
    public DressingService dressingService;
    public AppointmentTMService appointmentTMService;
    public DressingAppointmentService dressingAppointmentService;
    public SalonSAppointmentService salonSAppointmentService;
    private AppointmentDTO appointment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colAppId.setCellValueFactory(new PropertyValueFactory<>("appId"));
        colCid.setCellValueFactory(new PropertyValueFactory<>("CID"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSalonService.setCellValueFactory(new PropertyValueFactory<>("SalonService"));
        colDressing.setCellValueFactory(new PropertyValueFactory<>("DressingId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));

        try {
            this.appointmentService= ServiceFactory.getInstance().getService(ServiceTypes.APPOINTMENT);
            this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
            this.salonSService= ServiceFactory.getInstance().getService(ServiceTypes.SALONSERVICE);
            this.dressingService= ServiceFactory.getInstance().getService(ServiceTypes.DRESSING);
            this.appointmentTMService= ServiceFactory.getInstance().getService(ServiceTypes.APPOINTMENTTM);
            this.dressingAppointmentService= ServiceFactory.getInstance().getService(ServiceTypes.DRESSINGAPPOINTMENT);
            this.salonSAppointmentService= ServiceFactory.getInstance().getService(ServiceTypes.SALONSAPPOINTMENT);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadNextAppId();
        loadCustomerIDs();
        loadAppointmentTableDetails();
        loadDressingIDs();
        loadServiceIDs();
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        AppointmentTMDTO selectedItem = (AppointmentTMDTO) tblAppointment.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fillData(selectedItem);
        String appId = selectedItem.getAppId();
        AppointmentDTO appointment = appointmentService.search(appId);
        lblTotalCost.setText(String.valueOf(appointment.getCost()));
    }

    public void txtSearchAppointmentOnAction(ActionEvent actionEvent) {
        String id = txtSearchAppointment.getText();
        try {
            AppointmentTMDTO appointmentTMDTO = appointmentTMService.search(id);
            if(appointmentTMDTO != null) {
                fillData(appointmentTMDTO);

            } else {
                new Alert(Alert.AlertType.WARNING, "Appointment Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtSearchAppointment.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String appId = lblAppointmentId.getText();
        String EmpId = lblEmployeeId.getText();
        String Cid = (String) cmbCustomerIds.getValue();
        String desc=txtDesc.getText();
        String date=txtDate.getText();
        String time=txtTime.getText();

        String SalonServices = (String) cmbSalonService.getValue();
        String Dressings = (String) cmbDressing.getValue();
        double totalCost = CalculateTotalCost();
        lblTotalCost.setText(String.valueOf(totalCost));
        //double tot = Double.parseDouble(lblTotalCost.getText());
        String SSid = salonSService.searchName(SalonServices);
        System.out.println(SSid);
        String Did = dressingService.searchName(Dressings);
        System.out.println(Did);

        Appointment appointment = new Appointment(appId,EmpId,Cid,desc,date,time,totalCost);
        AppointmentTM appointmentTM=new AppointmentTM(appId,Cid,desc,SalonServices,Dressings,date,time);
        //-----------------------------------------------------------------------------------------------
        Dressingappointmentdetail dressingappointmentdetail=new Dressingappointmentdetail(appId,Did,date);
        Salonsappointmentdetail salonsappointmentdetail=new Salonsappointmentdetail(appId,SSid,date);
        /*try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isAppTMAdded = appointmentTMService.save(appointmentTM);
            System.out.println(isAppTMAdded);
            if(isAppTMAdded) {
                boolean isAdded = appointmentService.save(appointment);
                System.out.println(isAdded);
                if(isAdded) {
                    boolean isSSAdded = salonSAppointmentService.save(salonsappointmentdetail);
                    System.out.println("isSSAdded : "+isSSAdded);
                    boolean isDressingAdded = dressingAppointmentService.save(dressingappointmentdetail);
                    System.out.println("isDre : " + isDressingAdded);
                    if(isSSAdded | isDressingAdded) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Appointment Added!").show();
                        DBConnection.getInstance().getConnection().commit();
                    }else{
                        new Alert(Alert.AlertType.ERROR, "Cannot add Appointment!").show();
                    }
                }
            }
            DBConnection.getInstance().getConnection().rollback();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally{
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }*/

        boolean saveTransaction = appointmentService.saveTransaction(appointmentTM, appointment, salonsappointmentdetail, dressingappointmentdetail);

        if(saveTransaction != true){
            new Alert(Alert.AlertType.ERROR, "Cannot add Appointment!").show();
        }else{
            new Alert(Alert.AlertType.CONFIRMATION, "Appointment Added!").show();
        }

        loadAppointmentTableDetails();
        clearFields();
        loadNextAppId();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String appId = lblAppointmentId.getText();
        String EmpId = lblEmployeeId.getText();
        String Cid = (String) cmbCustomerIds.getValue();
        String desc=txtDesc.getText();
        String date=txtDate.getText();
        String time=txtTime.getText();
        String SalonServices = (String) cmbSalonService.getValue();
        String Dressings = (String) cmbDressing.getValue();
        String SSid = salonSService.searchName(SalonServices);
        System.out.println(SSid);
        String Did = dressingService.searchName(Dressings);
        System.out.println(Did);

        double totalCost = CalculateTotalCost();
        lblTotalCost.setText(String.valueOf(totalCost));
        //double tot = Double.parseDouble(lblTotalCost.getText());

        AppointmentDTO appointment = new AppointmentDTO(appId,EmpId,Cid,desc,date,time,totalCost);
        AppointmentTMDTO appointmentTM=new AppointmentTMDTO(appId,Cid,desc,SalonServices,Dressings,date,time);
        DressingappointmentdetailDTO dressingappointmentdetail=new DressingappointmentdetailDTO(appId,Did,date);
        SalonsappointmentdetailDTO salonsappointmentdetail=new SalonsappointmentdetailDTO(appId,SSid,date);
        try {
            boolean isUpdate = appointmentService.update(appointment);
            boolean isTMupdated = appointmentTMService.update(appointmentTM);
            boolean isDerssUpdated = dressingAppointmentService.update(dressingappointmentdetail);
            boolean isSSUpdated = salonSAppointmentService.update(salonsappointmentdetail);
            if (isUpdate & isTMupdated & isDerssUpdated & isSSUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Update Appointment!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadAppointmentTableDetails();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        AppointmentTMDTO selectedItem = (AppointmentTMDTO) tblAppointment.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String appId = selectedItem.getAppId();
        try {
            boolean isSSAppDeleted = salonSAppointmentService.delete(appId);
            boolean isDressingAppDeleted = dressingAppointmentService.delete(appId);
            boolean isDeleted=appointmentService.delete(appId);
            boolean isTMdeleted = appointmentTMService.delete(appId);

            if(isSSAppDeleted & isDressingAppDeleted ){
                if(isDeleted | isTMdeleted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Appointment Deleted!").show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Cannot Find Appointment!").show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(DeleteCustomerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadAppointmentTableDetails();
        clearFields();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnAppointmentPaymentOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        String appointmentIdText = lblAppointmentId.getText();
        AppointmentDTO appointment = appointmentService.search(appointmentIdText);
        this.appointment=appointment;
        Navigation.navigate(Routes.MAKEPAYMENT_FORM,pane);
    }

    public void btnAddCustOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/rangabeautysalon/view/customer/AddCustomerForm.fxml"));
        Parent load = fxmlLoader.load();
        Stage stage=new Stage();
        stage.setTitle("Add Customer Form");
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void cmbSalonServiceOnAction(ActionEvent actionEvent) {

    }

    public void cmbDressingOnAction(ActionEvent actionEvent) {
    }

    public void cmbCustIdsOnAction(ActionEvent actionEvent) {
    }

    public void loadNextAppId(){
        try {
            String nextAppointmentId = appointmentService.generateNextAppointmentId();
            lblAppointmentId.setText(nextAppointmentId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomerIDs(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = customerService.loadCustomerIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerIds.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadServiceIDs(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> NameList = salonSService.loadSSNames();

            for (String name : NameList) {
                observableList.add(name);
            }
            cmbSalonService.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDressingIDs(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> NameList = dressingService.loadDressingNames();

            for (String name : NameList) {
                observableList.add(name);
            }
            cmbDressing.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAppointmentTableDetails(){
        try {
            List<AppointmentTMDTO> allAppointmentTMs = appointmentTMService.getAllAppointmentTMs();

            ObservableList<AppointmentTMDTO> AppointmentTMDTOs = FXCollections.observableArrayList();
            for(AppointmentTMDTO atm : allAppointmentTMs){
                AppointmentTMDTO appointmentTM=new AppointmentTMDTO(atm.getAppId(),atm.getCID(),atm.getDescription(),atm.getSalonService(),atm.getDressingId(),atm.getDate(),atm.getTime());
                AppointmentTMDTOs.add(appointmentTM);
            }
            tblAppointment.setItems(AppointmentTMDTOs);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        /*AppointmentTM appointmentTM=new AppointmentTM(lblAppointmentId.getText(),cmbCustomerIds.getAccessibleText(),txtDesc.getText(),cmbSalonService.getAccessibleText(),cmbDressing.getAccessibleText(),txtDate.getText(),txtTime.getText());
        ObservableList<AppointmentTM> AppointmentTMs = FXCollections.observableArrayList();
        tblAppointment.setItems(AppointmentTMs);*/
    }

    private void fillData(AppointmentTMDTO appointment) {
        lblAppointmentId.setText(appointment.getAppId());
        cmbCustomerIds.setValue(appointment.getCID());
        txtDesc.setText(appointment.getDescription());
        txtDate.setText(appointment.getDate());
        txtTime.setText(appointment.getTime());
        cmbDressing.setValue(appointment.getDressingId());
        cmbSalonService.setValue(appointment.getSalonService());

    }

    public void clearFields(){
        lblAppointmentId.setText("");
        txtDesc.setText("");
        txtDate.setText("");
        txtTime.setText("");
        cmbDressing.setValue("");
        cmbSalonService.setValue("");
        cmbCustomerIds.setValue("");
        lblTotalCost.setText("");
        loadNextAppId();
    }

    public double CalculateTotalCost() throws SQLException, ClassNotFoundException {
        String SalonServices = (String) cmbSalonService.getValue();
        System.out.println(SalonServices);
        String Dressings = (String) cmbDressing.getValue();
        System.out.println(Dressings);

        double Servicecost=0;
        double dressingCost=0;

        if(SalonServices != null ){
            SalonServiceDTO salonService = salonSService.searchForObject(SalonServices);
            if(salonService != null){
                Servicecost = salonService.getCost();
            }else{
                Servicecost=0;
            }
        }

        if(Dressings != null ){
            DressingDTO dressing = dressingService.searchForObject(Dressings);
            if(dressing != null){
                dressingCost = dressing.getCost();
            }else{
                dressingCost=0;
            }
        }

        /*SalonServiceDTO salonService = salonSService.searchForObject(SalonServices);
        if(salonService != null){
            Servicecost = salonService.getCost();
        }else{
            Servicecost=0;
        }
        //------------------------------------------------------------------------------------
        DressingDTO dressing = dressingService.searchForObject(Dressings);
        if(dressing != null){
            dressingCost = dressing.getCost();
        }else{
            dressingCost=0;
        }*/

        double Tot=Servicecost+dressingCost;
        return Tot;
    }

    public AppointmentDTO getAppointment(){
        return this.appointment;
    }

}
