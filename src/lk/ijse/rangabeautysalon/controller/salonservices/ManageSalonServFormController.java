package lk.ijse.rangabeautysalon.controller.salonservices;

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
import javafx.scene.layout.AnchorPane;
import lk.ijse.rangabeautysalon.dto.SalonServiceDTO;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageSalonServFormController implements Initializable {
    public TableView<SalonServiceDTO> tblSalonService;
    public JFXTextField txtServiceId;
    public JFXTextField txtName;
    public JFXTextField txtCost;
    public AnchorPane pane;
    public TextField txtSearchSalonService;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCost;

    public SalonSService salonSService;

    public void txtSearchServiceOnAction(ActionEvent actionEvent) {
        String id = txtSearchSalonService.getText();
        try {
            SalonServiceDTO salonService = salonSService.search(id);
            if (salonService != null) {
                fillData(salonService);

            } else {
                new Alert(Alert.AlertType.WARNING, "Salon Service Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtSearchSalonService.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtServiceId.getText();
        String name = txtName.getText();
        double cost = Double.parseDouble(txtCost.getText());
        ;

        SalonServiceDTO salonService = new SalonServiceDTO(id, name, cost);
        try {
            boolean isAdded = salonSService.save(salonService);
            if (isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salon Service Detail Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadSalonService();
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtServiceId.getText();
        String name = txtName.getText();
        double cost = Double.parseDouble(txtCost.getText());
        ;

        SalonServiceDTO salonService = new SalonServiceDTO(id, name, cost);
        try {
            boolean isUpdate = salonSService.update(salonService);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Update Salon Service!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadSalonService();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        SalonServiceDTO selectedItem = (SalonServiceDTO) tblSalonService.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String SSid = selectedItem.getSSId();
        try {
            boolean isDeleted = salonSService.delete(SSid);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salon Service Deleted!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Find Salon Service!").show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
        }
        loadSalonService();
        clearFields();
    }


    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void fillData(SalonServiceDTO salonService) {
        txtServiceId.setText(salonService.getSSId());
        txtName.setText(salonService.getServiceName());
        txtCost.setText(String.valueOf(salonService.getCost()));
    }

    public void loadSalonService() {
        try {
            ArrayList<SalonServiceDTO> allSalonServices = salonSService.getAll();
            ObservableList<SalonServiceDTO> dressings = FXCollections.observableArrayList();
            for (SalonServiceDTO d : allSalonServices) {
                SalonServiceDTO salonService = new SalonServiceDTO(d.getSSId(), d.getServiceName(), d.getCost());
                System.out.println(salonService);
                dressings.add(salonService);
            }
            tblSalonService.setItems(dressings);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearFields() {
        txtServiceId.setText("");
        txtName.setText("");
        txtCost.setText("");
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) {
        SalonServiceDTO selectedItem = (SalonServiceDTO) tblSalonService.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fillData(selectedItem);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(new PropertyValueFactory<>("SSId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ServiceName"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            this.salonSService= ServiceFactory.getInstance().getService(ServiceTypes.SALONSERVICE);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadSalonService();
    }

}
