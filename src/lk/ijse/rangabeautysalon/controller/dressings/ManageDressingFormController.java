package lk.ijse.rangabeautysalon.controller.dressings;
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
import lk.ijse.rangabeautysalon.dto.DressingDTO;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageDressingFormController implements Initializable {
    public JFXTextField txtDressingId;
    public JFXTextField txtType;
    public JFXTextField txtCost;
    public TableView<DressingDTO> tblDressing;
    public AnchorPane pane;
    public TableColumn colDressingId;
    public TableColumn colDressingType;
    public TableColumn colCost;
    public TextField txtSearchDressing;

    public DressingService dressingService;

    public void txtSearchDressingOnAction(ActionEvent actionEvent) {
        String id = txtSearchDressing.getText();
        try {
            DressingDTO dressing = dressingService.search(id);
            if(dressing != null) {
                fillData(dressing);

            } else {
                new Alert(Alert.AlertType.WARNING, "Dressing Type Not Found!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtSearchDressing.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtDressingId.getText();
        String type = txtType.getText();
        double cost = Double.parseDouble(txtCost.getText());;

        DressingDTO dressing = new DressingDTO(id, type, cost);
        try {
            boolean isAdded = dressingService.save(dressing);
            if(isAdded) {
                new Alert(Alert.AlertType.CONFIRMATION, "Dressing Detail Added!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        loadDressings();
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtDressingId.getText();
        String type = txtType.getText();
        double cost = Double.parseDouble(txtCost.getText());;

        DressingDTO dressing = new DressingDTO(id, type, cost);

        try {
            boolean isUpdate = dressingService.update(dressing);
            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Cannot Update DressingDetail!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadDressings();
        clearFields();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        DressingDTO selectedItem = (DressingDTO) tblDressing.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String dressingId = selectedItem.getDressingId();
        try {
            boolean isDeleted=dressingService.delete(dressingId);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "DressingDetail Deleted!").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Cannot Find DressingDetail!").show();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(DeleteCustomerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadDressings();
        clearFields();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDressingId.setCellValueFactory(new PropertyValueFactory<>("DressingId"));
        colDressingType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        try {
            this.dressingService= ServiceFactory.getInstance().getService(ServiceTypes.DRESSING);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        loadDressings();
    }

    private void fillData(DressingDTO dressing) {
        txtDressingId.setText(dressing.getDressingId());
        txtType.setText(dressing.getType());
        txtCost.setText(String.valueOf(dressing.getCost()));
    }

    public void loadDressings()  {
        try {
            ArrayList<DressingDTO> allDressing = dressingService.getAllDressing();
            ObservableList<DressingDTO> dressings= FXCollections.observableArrayList();
            for(DressingDTO d : allDressing){
                DressingDTO dressing=new DressingDTO(d.getDressingId(),d.getType(),d.getCost());
                System.out.println(dressing);
                dressings.add(dressing);
            }
            tblDressing.setItems(dressings);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void clearFields(){
        txtDressingId.setText("");
        txtType.setText("");
        txtCost.setText("");
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) {
        DressingDTO selectedItem = (DressingDTO) tblDressing.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        fillData(selectedItem);
    }
}
