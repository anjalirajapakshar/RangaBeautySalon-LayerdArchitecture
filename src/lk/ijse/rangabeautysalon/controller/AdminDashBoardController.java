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

public class AdminDashBoardController implements Initializable {

    public AnchorPane MainPane;
    public AnchorPane SubWindowsPane;
    public Label lblDate;
    public Label lblTime;
    private volatile boolean stop;

    public void btnCloseOnAction(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) MainPane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/LoginForm.fxml"))));
        window.centerOnScreen();
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGEEMP_FORM,SubWindowsPane);
    }



    public void btnIncomeROnAction(ActionEvent actionEvent) {

    }

    public void btnProductOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.MANAGEPRODUCT_FORM,SubWindowsPane);
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
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
    }

    public void setDate(){
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDate();
        timenow();
    }
}
