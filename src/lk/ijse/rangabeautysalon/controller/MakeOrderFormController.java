package lk.ijse.rangabeautysalon.controller;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.rangabeautysalon.db.DBConnection;
import lk.ijse.rangabeautysalon.dto.*;
import lk.ijse.rangabeautysalon.entity.Order;
import lk.ijse.rangabeautysalon.entity.OrderProductDetail;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;
import lk.ijse.rangabeautysalon.util.Navigation;
import lk.ijse.rangabeautysalon.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakeOrderFormController   implements Initializable {
    public AnchorPane pane;
    public JFXComboBox cmbCustomerId;
    public TableView<OrderTMDTO> tblOrderDetail;
    public TableColumn colProductId;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotalCost;

    public JFXTextField txtDescription;
    public JFXTextField txtQtyYouWant;
    public Label lblTotalCost;
    public Label lblEmployeeId;
    public Label lblCustomerName;
    public JFXDatePicker DatePicker;
    public Label lblOrderId;
    public Label lblQtyOnHand;
    public Label lblUnitPrice;
    public JFXComboBox cmbProductname;
    private ObservableList<OrderTMDTO> obList = FXCollections.observableArrayList();
    public OrderDTO order;
    public OrderService orderService;
    public ProductService productService;
    public CustomerService customerService;
    public OrderProductService orderProductService;


    public MakeOrderFormController() {

    }

    public void btnBackOnAction(ActionEvent actionEvent) {

    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/rangabeautysalon/view/customer/AddCustomerForm.fxml"));
        Parent load = fxmlLoader.load();
        Stage stage=new Stage();
        stage.setTitle("Add Customer Form");
        stage.setScene(new Scene(load));
        stage.show();
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        OrderTMDTO selectedItem = (OrderTMDTO) tblOrderDetail.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fillData(selectedItem);
    }

    private void fillData(OrderTMDTO selectedItem) throws SQLException, ClassNotFoundException {
        ProductDTO product = productService.search(selectedItem.getProductId());
        cmbProductname.setValue(product.getName());
        lblUnitPrice.setText(String.valueOf(product.getCost()));
        lblQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));
        txtQtyYouWant.setText(String.valueOf(selectedItem.getQty()));
        txtDescription.setText(selectedItem.getDescription());
    }

    //------------------------------------------------------------------------------------------------------------------
    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String empid = lblEmployeeId.getText();
        String custid = (String) cmbCustomerId.getValue();
        String custname = lblCustomerName.getText();
        String date = String.valueOf(DatePicker.getValue());
        String orderid = lblOrderId.getText();
        double totalCostText = Double.parseDouble(lblTotalCost.getText());
        /*for (int i = 0; i < tblOrderDetail.getItems().size(); i++){
            String colProductIdCellData = (String) colProductId.getCellData(i);
            int colQtyCellData = (int) colQty.getCellData(i);
            OrderProductDetail orderProductDetail=new OrderProductDetail(orderid,colProductIdCellData,date,colQtyCellData);
            boolean isOrderProductDetailAdded = OrderProductModel.save(orderProductDetail);
            if(isOrderProductDetailAdded) {
                DBConnection.getInstance().getConnection().commit();
                new Alert(Alert.AlertType.CONFIRMATION, "Order Added Successfully !!!").show();
            }
        }*/

        OrderDTO order=new OrderDTO(orderid,date,empid,custid,totalCostText);

        //boolean orderTransaction = orderService.OrderTransaction(order);
        try {
            DBConnection.getInstance().getConnection().setAutoCommit(false);
            boolean isOrderAdded = orderService.save(order);
            if (isOrderAdded ) {
                for (int i = 0; i < tblOrderDetail.getItems().size(); i++){
                    String colProductIdCellData = (String) colProductId.getCellData(i);
                    int colQtyCellData = (int) colQty.getCellData(i);
                    OrderProductDetailDTO orderProductDetail= new OrderProductDetailDTO(orderid,colProductIdCellData,date,colQtyCellData);
                    boolean isOrderProductDetailAdded = orderProductService.save(orderProductDetail);
                    if(isOrderProductDetailAdded) {
                        DBConnection.getInstance().getConnection().commit();
                        new Alert(Alert.AlertType.CONFIRMATION, "Order Added Successfully !!!").show();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Cannot Add Order!!!").show();
                    }
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Cannot Add Order!!!").show();
            }
            DBConnection.getInstance().getConnection().rollback();
        } finally {
            DBConnection.getInstance().getConnection().setAutoCommit(true);
        }

        loadNextOrderId();
        tblOrderDetail.getItems().clear();
        //cmbCustomerId.setValue(null);
        lblCustomerName.setText("");
        clearFields();
        DatePicker.getEditor().clear();
        lblTotalCost.setText("");
    }
    //-------------------------------------------------------------------------------------------------------------------
    public void btnAddProductDetailsOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = String.valueOf(cmbProductname.getValue());
        ProductDTO product = productService.searchName(name);

        String productId = product.getProductId();
        int qty = Integer.parseInt(txtQtyYouWant.getText());
        String desc = txtDescription.getText();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * qty;

        if (!obList.isEmpty()) {
            L1:
            for (int i = 0; i < tblOrderDetail.getItems().size(); i++) {
                if (colProductId.getCellData(i).equals(productId)) {
                    qty += (int) colQty.getCellData(i);
                    total = unitPrice * qty;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotalCost(total);
                    tblOrderDetail.refresh();
                    return;
                }
            }
        }
        obList.add(new OrderTMDTO(productId, desc, qty, unitPrice, total));
        System.out.println(obList);
        tblOrderDetail.setItems(obList);
        clearFields();
        setLblTotalCost();
    }

    public void btnClearProductDetailsOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void clearFields(){
        cmbProductname.setValue("");
        lblUnitPrice.setText("");
        lblQtyOnHand.setText("");
        txtDescription.setText("");
        txtQtyYouWant.setText("");
    }

    public void btnGoToPaymentOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        /*String orderIdText = lblOrderId.getText();
        OrderDTO order = orderService.search(orderIdText);
        this.order=order;*/
        Navigation.navigate(Routes.MAKEPAYMENT_FORM,pane);
    }

    public OrderDTO getOrder(){
        return this.order;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        OrderTMDTO selectedItem = (OrderTMDTO) tblOrderDetail.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);

        String productId = selectedItem.getProductId();
        for (int i=0 ; i < obList.size() ; i++){
            if(colProductId.getCellData(i).equals(productId)){
                tblOrderDetail.getItems().remove(selectedItem);
                clearFields();
            }else{
                new Alert(Alert.AlertType.ERROR,("Cannot Remove Data!!")).show();
            }
        }
        setLblTotalCost();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    /*public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) pane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/ReceptionistDashBoard.fxml"))));
        window.centerOnScreen();
    }*/

    private void loadNextOrderId() {
        try {
            String orderId = orderService.generateNextOrderId();
            lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadCustomerIDs(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = customerService.loadCustomerIds();

            for (String id : idList) {
                observableList.add(id);
            }
            cmbCustomerId.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadProductNames(){
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> NameList = productService.loadProductNames();

            for (String name : NameList) {
                observableList.add(name);
            }
            cmbProductname.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colProductId.setCellValueFactory(new PropertyValueFactory("ProductId"));
        colDescription.setCellValueFactory(new PropertyValueFactory("Description"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory("UnitPrice"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory("TotalCost"));

        try {
            this.orderService= ServiceFactory.getInstance().getService(ServiceTypes.ORDER);
            this.productService= ServiceFactory.getInstance().getService(ServiceTypes.PRODUCT);
            this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
            this.orderProductService= ServiceFactory.getInstance().getService(ServiceTypes.ORDERPRODUCT);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadNextOrderId();
        loadCustomerIDs();
        loadProductNames();
    }

    public void cmbCustOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = String.valueOf(cmbCustomerId.getValue());
        System.out.println(customerId);
        CustomerDTO search = customerService.search(customerId);
        System.out.println(search.toString());
        String name = search.getName();
        System.out.println(name);
        lblCustomerName.setText(name);
    }

    public void cmbProductNameOnACtion(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = String.valueOf(cmbProductname.getValue());
        System.out.println(name);
        ProductDTO product = productService.searchName(name);
        if(product != null) {
            lblQtyOnHand.setText(String.valueOf(product.getQtyOnHand()));
            lblUnitPrice.setText(String.valueOf(product.getCost()));
            String ProductId=product.getProductId();
        }
    }

    public void setLblTotalCost(){
        double total = (double) colTotalCost.getCellData(0);
        int i=1;
        while (i < tblOrderDetail.getItems().size()) {
            total += (double) colTotalCost.getCellData(i);
            i++;
        }
        lblTotalCost.setText(String.valueOf(total));
    }
}
