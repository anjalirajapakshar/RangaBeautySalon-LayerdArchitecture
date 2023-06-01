package lk.ijse.rangabeautysalon.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.ijse.rangabeautysalon.util.Navigation;
import lk.ijse.rangabeautysalon.util.Routes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class LoginFormController implements Initializable {
    public AnchorPane pane;
    public AnchorPane subPane;
    public Pattern userNamePattern;
    public  Pattern PasswordPattern;
    public JFXTextField txtUserName;
    public JFXPasswordField PasswordField;
    public JFXTextField PasswordText;
    public JFXCheckBox cbShowPassword;

    public AnchorPane getPane() {
        return pane;
    }

    public void btnExitOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNamePattern = Pattern.compile("^[a-zA-Z]{6,}$");
        PasswordPattern = Pattern.compile("^[a-zA-Z0-9]{6,}$");

        changeVisibility();
        txtUserName.requestFocus();
    }

    public void lblSignUpOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.SIGNUP_FORM,subPane);
    }

    public void btnSignInOnAction(ActionEvent actionEvent) throws IOException {
        boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        boolean isPasswordMatched = PasswordPattern.matcher(PasswordField.getText()).matches();

        if(isUserNameMatched) {
            if(isPasswordMatched) {
                if(txtUserName.getText().equalsIgnoreCase("AdminRanga") & PasswordField.getText().equalsIgnoreCase("ranga1234")){
                    Stage window =(Stage) pane.getScene().getWindow();
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/AdminDashBoard.fxml"))));
                    window.centerOnScreen();
                    window.setTitle("AdminDashBoard Form");
                }else{
                    if(txtUserName.getText().equalsIgnoreCase("recepanuradha") & PasswordField.getText().equalsIgnoreCase("anu1234")){
                        Stage window =(Stage) pane.getScene().getWindow();
                        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/ReceptionistDashBoard.fxml"))));
                        window.centerOnScreen();
                        window.setTitle("ReceptionistDashBoard Form");
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Incorrect UserName or Password !!").show();
                        txtUserName.setText("");
                        PasswordField.setText("");
                        txtUserName.requestFocus();
                    }
                }
            } else {
                PasswordField.setFocusColor(Paint.valueOf("Red"));
                PasswordField.requestFocus();
            }
        } else {
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
        }
    }

    public void txtUserNameOnAction(KeyEvent actionEvent) {
        boolean isuserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        if(!isuserNameMatched){
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
        }else{
            txtUserName.setFocusColor(Paint.valueOf("Blue"));
        }
    }

    /*public void txtPwOnAction(KeyEvent actionEvent) {
        boolean isPasswordMatched = PasswordPattern.matcher(txtPassword.getText()).matches();
        if(!isPasswordMatched){
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            txtPassword.requestFocus();
        }else{
            txtPassword.setFocusColor(Paint.valueOf("Blue"));
        }
    }*/

    public void PasswordFieldOnAction(ActionEvent actionEvent) {
        boolean isPasswordMatched = PasswordPattern.matcher(PasswordField.getText()).matches();
        if(!isPasswordMatched){
            PasswordField.setFocusColor(Paint.valueOf("Red"));
            PasswordField.requestFocus();
        }else{
            PasswordField.setFocusColor(Paint.valueOf("Blue"));
        }
    }

    public void changeVisibility(){
        if(cbShowPassword.isSelected()){
            PasswordText.setText(PasswordField.getText());
            PasswordText.setVisible(true);
            PasswordField.setVisible(false);
            return;
        }
        PasswordField.setText(PasswordText.getText());
        PasswordField.setVisible(true);
        PasswordText.setVisible(false);
    }
}