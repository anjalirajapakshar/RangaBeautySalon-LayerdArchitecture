package lk.ijse.rangabeautysalon.controller.employee;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.rangabeautysalon.dto.EmployeeDTO;
import lk.ijse.rangabeautysalon.entity.Employee;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageEmployeeFormController implements Initializable {
    public TableView<EmployeeDTO> tblEmployee;
    public JFXTextField txtEId;
    public JFXTextField txtEName;
    public JFXTextField txtEAddress;
    public JFXTextField txtEtel;
    public JFXTextField txtENic;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colTel;
    public TableColumn colNic;
    public TextField txtSearchEmployee;

    public EmployeeService employeeService;

    public void txtSearchEmployeeOnAction(ActionEvent actionEvent) {
        String id = txtSearchEmployee.getText();
        try {
            EmployeeDTO employee = employeeService.search(id);
            if(employee != null) {
                fillData(employee);

            } else {
                new Alert(Alert.AlertType.WARNING, "Employee Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtSearchEmployee.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtEId.getText();
        String name = txtEName.getText();
        String address = txtEAddress.getText();
        int tel=Integer.parseInt(txtEtel.getText());
        String nic=txtENic.getText();

        EmployeeDTO employee = new EmployeeDTO(id, name, address, tel, nic);
        try {
            boolean isAdded = employeeService.save(employee);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadEmployees();
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtEId.getText();
        String name = txtEName.getText();
        String address = txtEAddress.getText();
        int tel=Integer.parseInt(txtEtel.getText());
        String nic=txtENic.getText();

        EmployeeDTO employee = new EmployeeDTO(id, name, address, tel, nic);
        try {
            boolean isUpdate = employeeService.update(employee);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Update Employee!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadEmployees();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        EmployeeDTO selectedItem = (EmployeeDTO) tblEmployee.getSelectionModel().getSelectedItem();
        String empId = selectedItem.getEmpId();
        try {
            boolean isDeleted=employeeService.delete(empId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee Deleted!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Cannot Find Employee!").show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(DeleteCustomerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadEmployees();
        clearFields();
    }

    private void fillData(EmployeeDTO employee) {
        txtEId.setText(employee.getEmpId());
        txtEName.setText(employee.getName());
        txtEAddress.setText(employee.getAddress());
        txtEtel.setText(String.valueOf(employee.getTel()));
        txtENic.setText(employee.getNic());
    }

    public void loadEmployees()  {
        try {
            ArrayList<EmployeeDTO> allEmployees = employeeService.getAllEmployee();
            ObservableList<EmployeeDTO> employees= FXCollections.observableArrayList();
            for(EmployeeDTO e : allEmployees){
                EmployeeDTO employee=new EmployeeDTO(e.getEmpId(),e.getName(),e.getAddress(),e.getTel(),e.getNic());
                System.out.println(employee);
                employees.add(employee);
            }
            tblEmployee.setItems(employees);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearFields(){
        txtEId.setText("");
        txtEName.setText("");
        txtEAddress.setText("");
        txtEtel.setText("");
        txtENic.setText("");
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("EmpId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));

        try {
            this.employeeService= ServiceFactory.getInstance().getService(ServiceTypes.EMPLOYEE);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadEmployees();
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) {
        EmployeeDTO selectedItem = (EmployeeDTO) tblEmployee.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fillData(selectedItem);
    }

}
