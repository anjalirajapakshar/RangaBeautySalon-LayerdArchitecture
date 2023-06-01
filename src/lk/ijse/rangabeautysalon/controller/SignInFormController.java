package lk.ijse.rangabeautysalon.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.rangabeautysalon.util.Navigation;
import lk.ijse.rangabeautysalon.util.Routes;

import java.io.IOException;

public class SignInFormController {

    public TextField txtPassword;
    public TextField txtEmail;
    public AnchorPane pane;


    public void lblSignUpOnAction(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.SIGNUP_FORM,pane);
    }

    public void btnSignInOnAction(ActionEvent actionEvent) {

    }
}
