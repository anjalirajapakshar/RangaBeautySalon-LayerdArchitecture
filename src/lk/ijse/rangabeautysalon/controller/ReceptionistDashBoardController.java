package lk.ijse.rangabeautysalon.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rangabeautysalon.util.Navigation;
import lk.ijse.rangabeautysalon.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class ReceptionistDashBoardController implements Initializable {
    public AnchorPane pane;
    public Label lblDate;
    public Label lblDateee;
    public AnchorPane subpane;
    private volatile boolean stop;

    public void btnClientOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGECUSTOMER_FORM,subpane);
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MAKEPAYMENT_FORM,subpane);
    }

    public void btnAppointmentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.APPOINTMENT_FORM,subpane);
    }

    public void btnDressingOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DRESSINGS_FORM,subpane);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ORDER_FORM,subpane);
    }

    public void btnServiceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SERVICE_FORM,subpane);
    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PRODUCT_FORM,subpane);
    }

    public void btnCloseOnAction(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) pane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/LoginForm.fxml"))));
        window.centerOnScreen();
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SUPPLIER_FORM,subpane);
    }

    private void timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    lblDate.setText(timenow);
                });
            }
        });
        thread.start();
    }

    public void setDate(){
        lblDateee.setText(String.valueOf(LocalDate.now()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        timenow();
    }
}
