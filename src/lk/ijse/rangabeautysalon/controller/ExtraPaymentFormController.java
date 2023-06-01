package lk.ijse.rangabeautysalon.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.rangabeautysalon.dto.ExtraExpenseDTO;
import lk.ijse.rangabeautysalon.service.custom.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExtraPaymentFormController implements Initializable {

    public JFXTextField txtExPayDesc;
    public JFXTextField txtExpayCost;
    public AnchorPane ExtraExPane;
    public Label lblExPayId;
    public MakePaymentFormController makePaymentFormController;

    public ExtraExpenseService extraExpenseService;

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String idText = lblExPayId.getText();
        String exPayDescText = txtExPayDesc.getText();
        double text = Double.parseDouble(txtExpayCost.getText());
        ExtraExpenseDTO extraExpense=new ExtraExpenseDTO(idText,exPayDescText,text);
        //getDetail(extraExpense);
        boolean save = extraExpenseService.save(extraExpense);
        if (save){
            new Alert(Alert.AlertType.CONFIRMATION,"Added Successfully !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Cannot Add Detail !!").show();
        }
        btnClearOnAction(actionEvent);
        Stage stage = (Stage) ExtraExPane.getScene().getWindow();
        stage.close();


    }

    /*public void getDetail(ExtraExpense expense){
        makePaymentFormController.getExtraExDetails(expense);
    }*/

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtExPayDesc.setText("");
        lblExPayId.setText("");
        txtExpayCost.setText("");
    }

    public void loadNextExEId(){
        try {
            String nextExExpenseId = extraExpenseService.generateNextExExpenseId();
            lblExPayId.setText(nextExExpenseId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadNextExEId();
    }

}
