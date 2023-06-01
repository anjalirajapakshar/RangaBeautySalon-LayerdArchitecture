package lk.ijse.rangabeautysalon.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage)Navigation.pane.getScene().getWindow();

        switch (route) {
            case MANAGEEMP_FORM:
                window.setTitle("Manage Employee Form");
                initUI("employee/ManageEmployeeForm.fxml");
                break;
            case MANAGECUSTOMER_FORM:
                window.setTitle("Manage Customer Form");
                initUI("customer/ManageCustomerForm.fxml");
                break;
            case MAKEPAYMENT_FORM:
                window.setTitle("Payment Form");
                initUI("MakePaymentForm.fxml");
                break;
            case MANAGEPRODUCT_FORM:
                window.setTitle("Manage Product Form");
                initUI("products/ManageProductForm.fxml");
                break;
            case SIGNUP_FORM:

                initUI("SignUpForm.fxml");
                break;
            case SIGNIN_FORM:

                initUI("LoginForm.fxml");
                break;
            case LOGIN_FORM:
                window.setTitle("Login Form");
                initUI("LoginForm.fxml");
                break;
            case APPOINTMENT_FORM:
                window.setTitle("Appointment Form");
                initUI("appointment/ManageAppointmentForm.fxml");
                break;
            case DRESSINGS_FORM:
                window.setTitle("Dressing Form");
                initUI("dressings/ManageDressingForm.fxml");
                break;
            case ORDER_FORM:
                window.setTitle("Order Form");
                initUI("MakeOrderForm.fxml");
                break;
            case SERVICE_FORM:
                window.setTitle("Salon Services Form");
                initUI("salonservices/ManageSalonServForm.fxml");
                break;
            case PRODUCT_FORM:
                window.setTitle("Product Form");
                initUI("products/ManageProductForm.fxml");
                break;
            case SUPPLIER_FORM:
                window.setTitle("Orders and Payments detail Form");
                initUI("supplier/ManageSupplierForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/rangabeautysalon/view/" + location)));
    }
}
