package lk.ijse.rangabeautysalon.controller;

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
import lk.ijse.rangabeautysalon.dto.RegistrationDTO;
import lk.ijse.rangabeautysalon.entity.Registration;
import lk.ijse.rangabeautysalon.service.ServiceFactory;
import lk.ijse.rangabeautysalon.service.ServiceTypes;
import lk.ijse.rangabeautysalon.service.custom.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignUpFormController implements Initializable {
    public AnchorPane pane;
    public LoginFormController loginFormController;
    public Pattern userNamePattern;
    public Pattern PasswordPattern;
    public Pattern RolePattern;
    public JFXTextField txtUserName;
    public JFXTextField txtRole;
    public JFXTextField txtPassword;
    public JFXTextField txtConfirmPassword;
    private Pattern PasswordConfirmPattern;
    public RegistrationService registrationService;


    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        boolean isUserNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        boolean isPasswordMatched = PasswordPattern.matcher(txtPassword.getText()).matches();
        boolean isRoleMatched = RolePattern.matcher(txtRole.getText()).matches();
        boolean isConfirmPasswordMatched = PasswordConfirmPattern.matcher(txtConfirmPassword.getText()).matches();

        if(isUserNameMatched){
            if(isRoleMatched){
                if(isPasswordMatched){
                    if(isConfirmPasswordMatched) {
                        if(txtPassword.getText().equals(txtConfirmPassword.getText())){
                            String userName = txtUserName.getText();
                            String password = txtPassword.getText();
                            String role = txtRole.getText();

                            RegistrationDTO registration = new RegistrationDTO(userName, password, role);
                            try {
                                boolean isAdded = registrationService.save(registration);
                                if (isAdded) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Sign-Up Successfully!").show();
                                }
                            } catch (SQLException | ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }

                            Stage window =(Stage) pane.getScene().getWindow();
                            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/LoginForm.fxml"))));
                            window.centerOnScreen();
                            window.setTitle("Login Form");
                            //Navigation.navigate(Routes.SIGNIN_FORM, loginFormController.getSubPane());
                            clearFields();

                        }else{
                            new Alert(Alert.AlertType.ERROR, "Password didn't match !! try again..!").show();
                            txtConfirmPassword.setText("");
                            txtConfirmPassword.requestFocus();
                        }
                    }else{
                        txtConfirmPassword.setFocusColor(Paint.valueOf("Red"));
                        txtConfirmPassword.requestFocus();
                    }
                }else{
                    txtPassword.setFocusColor(Paint.valueOf("Red"));
                    txtPassword.requestFocus();
                }
            }else{
                txtRole.setFocusColor(Paint.valueOf("Red"));
                txtRole.requestFocus();
            }
        }else{
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
        }
    }

    public void lblSignInOnAction(MouseEvent mouseEvent) throws IOException {
        Stage window =(Stage) pane.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/rangabeautysalon/view/LoginForm.fxml"))));
        window.centerOnScreen();
        window.setTitle("Login Form");
    }

    public void clearFields(){
        txtUserName.setText("");
        txtConfirmPassword.setText("");
        txtPassword.setText("");
        txtRole.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNamePattern = Pattern.compile("^[a-zA-Z]{6,}$");
        PasswordPattern = Pattern.compile("^[a-zA-Z0-9]{7,}$");
        RolePattern = Pattern.compile("^[a-zA-Z]{2,}$");
        PasswordConfirmPattern = Pattern.compile("^[a-zA-Z0-9]{7,}$");

        try {
            this.registrationService= ServiceFactory.getInstance().getService(ServiceTypes.REGISTRATION);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void txtUseNameOnAction(KeyEvent keyEvent) {
        boolean isUseNameMatched = userNamePattern.matcher(txtUserName.getText()).matches();
        if(!isUseNameMatched){
            txtUserName.setFocusColor(Paint.valueOf("Red"));
            txtUserName.requestFocus();
        }else{
            txtUserName.setFocusColor(Paint.valueOf("Blue"));
        }
    }

    public void txtRoleOnAction(KeyEvent keyEvent) {
        boolean isRoleMatched = RolePattern.matcher(txtRole.getText()).matches();
        if(!isRoleMatched){
            txtRole.setFocusColor(Paint.valueOf("Red"));
            txtRole.requestFocus();
        }else{
            txtRole.setFocusColor(Paint.valueOf("Blue"));
        }
    }

    public void txtPasswordOnAction(KeyEvent keyEvent) {
        boolean isPasswordMatched = PasswordPattern.matcher(txtPassword.getText()).matches();
        if(!isPasswordMatched){
            txtPassword.setFocusColor(Paint.valueOf("Red"));
            txtPassword.requestFocus();
        }else{
            txtPassword.setFocusColor(Paint.valueOf("Blue"));
        }
    }

    public void txtConfirmPasswordOnAction(KeyEvent keyEvent) {
        boolean isConfirmPasswordMatched = PasswordConfirmPattern.matcher(txtConfirmPassword.getText()).matches();
        if(!isConfirmPasswordMatched){
            txtConfirmPassword.setFocusColor(Paint.valueOf("Red"));
            txtConfirmPassword.requestFocus();
        }else{
            txtConfirmPassword.setFocusColor(Paint.valueOf("Blue"));
        }
    }
}
