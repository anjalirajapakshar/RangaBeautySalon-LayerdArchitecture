package lk.ijse.rangabeautysalon.controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rangabeautysalon.dao.custom.impl.CustomerImpl;
import lk.ijse.rangabeautysalon.dto.CustomerDTO;
import lk.ijse.rangabeautysalon.entity.Customer;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.CustomerService;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txttel;
    public JFXTextField txtNic;
    public AnchorPane pane;
    public TableView<CustomerDTO> tblCustomer;

    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTel;
    public TableColumn colNic;
    public TableColumn colID;
    public TextField txtSearchCustomer;

    public CustomerService customerService;


    public  void initialize() throws SQLException, ClassNotFoundException {
        colID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        this.customerService= ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);

        loadCustomers();
    }

    public void txtSearchCustomerOnAction(ActionEvent actionEvent) {
        String id = txtSearchCustomer.getText();
        try {
            CustomerDTO customer = customerService.search(id);
            if(customer != null) {
                fillData(customer);

            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtSearchCustomer.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel=Integer.parseInt(txttel.getText());
        String nic=txtNic.getText();

        CustomerDTO customer = new CustomerDTO(id, name, address, tel, nic);
        try {
            boolean isAdded = customerService.save(customer);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadCustomers();
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txttel.getText());
        String nic = txtNic.getText();
        CustomerDTO customer = new CustomerDTO(id, name, address, tel ,nic);
        try {
            boolean isUpdate = customerService.update(customer);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Update Customer!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadCustomers();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws NoSuchFieldException {
        CustomerDTO selectedItem = (CustomerDTO) tblCustomer.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String custID = selectedItem.getCustID();
        try {
            boolean isDeleted=customerService.deleteCustomer(custID);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Cannot Find Customer!").show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(DeleteCustomerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadCustomers();
        clearFields();
    }

    private void fillData(CustomerDTO customer) {
        txtId.setText(customer.getCustID());
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txttel.setText(String.valueOf(customer.getTel()));
        txtNic.setText(customer.getNic());
    }

    public void loadCustomers()  {
        try {
            ArrayList<CustomerDTO> allCustomer = (ArrayList<CustomerDTO>) customerService.getAllCustomer();
            ObservableList<CustomerDTO> customers= FXCollections.observableArrayList();
            for(CustomerDTO c : allCustomer){
                CustomerDTO customer=new CustomerDTO(c.getCustID(),c.getName(),c.getAddress(),c.getTel(),c.getNic());
                System.out.println(customer);
                customers.add(customer);
            }
            tblCustomer.setItems(customers);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearFields(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txttel.setText("");
        txtNic.setText("");
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) {
        CustomerDTO selectedItem = (CustomerDTO) tblCustomer.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        //String custID = selectedItem.getCustID();
        fillData(selectedItem);
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }
}
